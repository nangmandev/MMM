package com.spring.mmm.domain.mbtis.service;

import com.spring.mmm.common.service.RedisRepository;
import com.spring.mmm.domain.mbtis.controller.request.MukBTICalcInfo;
import com.spring.mmm.domain.mbtis.controller.request.MukBTICalcRequest;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResponse;
import com.spring.mmm.domain.mbtis.controller.response.MukBTIResult;
import com.spring.mmm.domain.mbtis.domain.*;
import com.spring.mmm.domain.mbtis.exception.MukBTIErrorCode;
import com.spring.mmm.domain.mbtis.exception.MukBTIException;
import com.spring.mmm.domain.mbtis.service.port.MukBTIQuestionRepository;
import com.spring.mmm.domain.mbtis.service.port.MukBTIRepository;
import com.spring.mmm.domain.mbtis.service.port.MukBTIResultRepository;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.users.exception.UserErrorCode;
import com.spring.mmm.domain.users.exception.UserException;
import com.spring.mmm.domain.users.infra.UserEntity;
import com.spring.mmm.domain.users.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MukBTIServiceImpl implements MukBTIService {
    private final MukBTIQuestionRepository mukBTIQuestionRepository;
    private final RedisRepository redisRepository;
    private final MukBTIResultRepository mukBTIResultRepository;
    private final MukBTIRepository mukBTIRepository;
    private final UserRepository userRepository;

    @Override
    public List<MukBTIQuestionEntity> findAllMukBTIQuestion() {
        return mukBTIQuestionRepository.findAllMukBTIQuestion();
    }

    @Override
    public MukBTIResult calcMBTI(MukBTICalcRequest mukBTICalcRequest) {
        for(MukBTICalcInfo mukBTICalcInfo : mukBTICalcRequest.getAnswers()){
            if(mukBTICalcInfo.getQuizId() == null || mukBTICalcInfo.getAnswerId() == null){
                throw new MukBTIException(MukBTIErrorCode.BAD_REQUEST);
            }
        }

        List<MukBTIQuestionEntity> questions = findAllMukBTIQuestion();

        int EI = 0, NS = 0, TF = 0, JP = 0, Mint = 0, Pine = 0, Die = 0;

        for(MukBTICalcInfo mukBTICalcInfo : mukBTICalcRequest.getAnswers()){
            MukBTIQuestionEntity question = matchQuestion(questions, mukBTICalcInfo.getQuizId());

            MukBTIAnswerEntity answer = matchAnswer(question.getMukBTIAnswerEntities(), mukBTICalcInfo.getAnswerId());

            switch (question.getMukBTIEntity().getType()) {
                case MukBTIType.EI -> EI += answer.getScore();
                case MukBTIType.NS -> NS += answer.getScore();
                case MukBTIType.TF -> TF += answer.getScore();
                case MukBTIType.JP -> JP += answer.getScore();
                case MukBTIType.MINT -> Mint += answer.getScore();
                case MukBTIType.PINE -> Pine += answer.getScore();
                case MukBTIType.DIE -> Die += answer.getScore();
            }
        }

        MBTI mbti = MBTI.builder()
                .ei(EI)
                .ns(NS)
                .tf(TF)
                .jp(JP)
                .mint(Mint)
                .pine(Pine)
                .die(Die)
                .build();

        String key = UUID.randomUUID().toString();
        redisRepository.saveData(key, mbti);
        return MukBTIResult.builder().key(key).mbti(mbti).build();
    }

    @Override
    @Transactional
    public void save(String email, String key) {
        MBTI mbti = redisRepository.getData(key, MBTI.class)
                .orElseThrow(() -> new MukBTIException(MukBTIErrorCode.NOT_FOUND));

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        user.recordMukBTIResult();
        MukboEntity mukboEntity = user.getMukboEntity();

        List<MukBTIResultEntity> originResult = user.getMukBTIResultEntities();
        mukBTIResultRepository.deleteAll(originResult);

        List<MukBTIResultEntity> results = new ArrayList<>();
        List<MukBTIEntity> mukBTIs = mukBTIRepository.findAllMukBTI();

        for(MukBTIEntity mukBTIEntity : mukBTIs){
            MukBTIResultEntity mukBTIResult = MukBTIResultEntity.createWithoutScore(mukBTIEntity, mukboEntity, user);
            switch (mukBTIEntity.getType()){
                case EI -> mukBTIResult.modifyScore(mbti.getEi());
                case NS -> mukBTIResult.modifyScore(mbti.getNs());
                case TF -> mukBTIResult.modifyScore(mbti.getTf());
                case JP -> mukBTIResult.modifyScore(mbti.getJp());
                case MINT -> mukBTIResult.modifyScore(mbti.getMint());
                case PINE -> mukBTIResult.modifyScore(mbti.getPine());
                case DIE -> mukBTIResult.modifyScore(mbti.getDie());
            }
            results.add(mukBTIResult);
        }

        mukBTIResultRepository.saveAll(results);
    }

    @Override
    public MukBTIResponse getMukBTI(String email) {
        List<MukBTIResultEntity> mukBTIResultEntities = mukBTIResultRepository.findAllMukBTIResultByMukboId(
                                userRepository.findByEmail(email)
                                        .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND))
                                .getMukboEntity()
                                .getMukboId()
                                );

        if(mukBTIResultEntities.size() == 0){
            throw new MukBTIException(MukBTIErrorCode.NOT_FOUND_ERROR);
        }

        return MukBTIResponse.builder()
                .mbti(MBTI.create(mukBTIResultEntities))
                .build();
    }

    private MukBTIQuestionEntity matchQuestion(List<MukBTIQuestionEntity> questions, Integer id){
        for(MukBTIQuestionEntity question : questions){
            if(question.getQuestionId().equals(id)){
                return question;
            }
        }
        throw new MukBTIException(MukBTIErrorCode.NOT_FOUND_QUESTION);
    }

    private MukBTIAnswerEntity matchAnswer(List<MukBTIAnswerEntity> answers, Integer id){
        for(MukBTIAnswerEntity answer : answers){
            if(answer.getAnswerId().equals(id)){
                return answer;
            }
        }
        throw new MukBTIException(MukBTIErrorCode.NOT_FOUND_ANSWER);
    }

}
