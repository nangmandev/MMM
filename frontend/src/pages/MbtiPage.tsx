import { useQuery } from '@tanstack/react-query';
import { getMbtiQuestionList } from '../api/mbtiApi';
import { useNavigate, useParams } from 'react-router-dom';
import styles from '../styles/mbtiPage/MbtiPage.module.css';
import buttonStyles from '../styles/common/Buttons.module.css';
import CheckCircle from '../components/mbtiPage/CheckCircle';
import userStore from '../stores/userStore';
import { useEffect, useState } from 'react';
import AnswerBox from '../components/mbtiPage/AnswerBox';

interface answerType {
  answerId: string;

  answerContent: string;
  answerImageSrc: string;
}

function MbtiPage() {
  const navigate = useNavigate();
  const [selectedAnswer, setSelectedAnswer] = useState('');
  const { updateAnswerList, answerList } = userStore();
  const { mbtiId } = useParams();

  const {
    data: questionList,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['mbtiQuestionList'],
    queryFn: getMbtiQuestionList,
  });
  
  useEffect(() => {
    setSelectedAnswer('');
  }, [mbtiId]);

  useEffect(() => {
    if (mbtiId === '0') {
      updateAnswerList([]);
    }
  }, []);

  const handleSelectAnswer = (answerId: string) => {
    setSelectedAnswer(answerId);
  };

  const goNextQuestion = () => {
    if (!selectedAnswer) return;

    const targetQuizId = questionList[Number(mbtiId)].quizId;

    const isExist = answerList.some(
      (answer) => answer.quizId === targetQuizId
    );

    if (isExist) {
      const updatedList = answerList.map((answer) => {
        if (answer.quizId === targetQuizId) {
          return { ...answer, answerId: selectedAnswer };
        }
        return answer;
      });
      updateAnswerList(updatedList);
    } else {
      updateAnswerList([
        ...answerList,
        {
          quizId: questionList[Number(mbtiId)].quizId,
          answerId: selectedAnswer,
        },
      ]);
    }

    if (mbtiId === '14') {
      navigate('/result');
    } else {
      navigate(`/mbti/${Number(mbtiId) + 1}`);
    }
  };

  if (isPending) {
    return <div>isLoding...</div>;
  }

  if (isError) {
    return <div>error</div>;
  }

  if (Number(mbtiId) === 8)
    return (
      <div className={styles.wrapperBaekBan}>
        <h1>{questionList[Number(mbtiId)].content}</h1>
        <div className={styles.questionNumBox}>
          {Number(mbtiId) + 1} / {questionList.length}
        </div>
        <img src={questionList[Number(mbtiId)].imageSrc} alt="" />
        <section>
          {questionList[Number(mbtiId)].answers.map(
            (answer: answerType) => {
              return (
                <div
                  key={answer.answerId}
                  onClick={() => handleSelectAnswer(answer.answerId)}
                >
                  <CheckCircle
                    isSelected={selectedAnswer == answer.answerId}
                  />
                  <span>{answer.answerContent}</span>
                </div>
              );
            }
          )}
        </section>
        <div className={styles.randomFoodBox}>
          <img className={styles.randomFood} alt="" />
        </div>
        <button
          onClick={goNextQuestion}
          className={buttonStyles.miniRoundedButton}
        >
          다음
        </button>
      </div>
    );

  if (questionList[Number(mbtiId)].answers.length === 5)
    return (
      <div className={styles.wrapperFive}>
        <h1>{questionList[Number(mbtiId)].content}</h1>
        <div className={styles.questionNumBox}>
          {Number(mbtiId) + 1} / {questionList.length}
        </div>
        <section>
          {questionList[Number(mbtiId)].answers.map(
            (answer: answerType) => {
              return (
                <div
                  key={answer.answerId}
                  onClick={() => handleSelectAnswer(answer.answerId)}
                >
                  <CheckCircle
                    isSelected={selectedAnswer == answer.answerId}
                  />
                  <span>{answer.answerContent}</span>
                  <div className={styles.imageBox}>
                    <img src={answer.answerImageSrc} alt="" />
                  </div>
                </div>
              );
            }
          )}
        </section>
        <div className={styles.randomFoodBox}>
          <img className={styles.randomFood} alt="" />
        </div>
        <button
          onClick={goNextQuestion}
          className={buttonStyles.miniRoundedButton}
        >
          다음
        </button>
      </div>
    );

  if (questionList[Number(mbtiId)].answers.length === 2)
    return (
      <div className={styles.wrapperTwo}>
        <h1>{questionList[Number(mbtiId)].content}</h1>
        <div className={styles.questionNumBox}>
          {Number(mbtiId) + 1} / {questionList.length}
        </div>
        <section>
          <AnswerBox
            handleSelectAnswer={() =>
              handleSelectAnswer(
                questionList[Number(mbtiId)].answers[0].answerId
              )
            }
            isSelected={
              selectedAnswer ==
              questionList[Number(mbtiId)].answers[0].answerId
            }
            text={
              questionList[Number(mbtiId)].answers[0].answerContent
            }
            imgSrc={
              questionList[Number(mbtiId)].answers[0].answerImageSrc
            }
          />
          <div className={styles.vsText}>VS</div>
          <AnswerBox
            handleSelectAnswer={() =>
              handleSelectAnswer(
                questionList[Number(mbtiId)].answers[1].answerId
              )
            }
            isSelected={
              selectedAnswer ==
              questionList[Number(mbtiId)].answers[1].answerId
            }
            text={
              questionList[Number(mbtiId)].answers[1].answerContent
            }
            imgSrc={
              questionList[Number(mbtiId)].answers[1].answerImageSrc
            }
          />
        </section>
        <div className={styles.randomFoodBox}>
          <img className={styles.randomFood} alt="" />
        </div>
        <button
          onClick={goNextQuestion}
          className={buttonStyles.miniRoundedButton}
        >
          다음
        </button>
      </div>
    );
}

export default MbtiPage;
