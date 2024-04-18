import { useEffect, useState } from 'react';
import {
  deleteMukbos,
  getEmailFind,
  getGroupMukbotList,
  getGroupUserList,
  postMukboInvite,
  postMukbotMake,
} from '../../api/memberApi';
import styles from '../../styles/groupPage/MemberSection.module.css';
import buttonStyles from '../../styles/common/Buttons.module.css';
import SubMemberArticle from '../common/SubMemberArticle';
import {
  useMutation,
  useQuery,
  useQueryClient,
} from '@tanstack/react-query';
import SubMemberCard from '../common/SubMemberCard';
import Modal from '../common/Modal';
import Input from '../common/Input';
import {
  checkEmailValidation,
  checkNicknameValidation,
} from '../../utils/validation';
import finder from '../../assets/images/finder.png';
import ConfirmModal from '../common/ConfirmModal';
import userStore from '../../stores/userStore';

interface propsType {
  groupId: number;
}

function MemberSection({ groupId }: propsType) {
  const { myMukboId } = userStore();
  const queryClient = useQueryClient();
  interface firstSecondType {
    eng: string;
    kor: string;
  }
  interface mbtiOptionsType {
    id: number;
    first: firstSecondType;
    second: firstSecondType;
    barColor: string;
    name: string;
  }
  const MBTI_OPTIONS: mbtiOptionsType[] = [
    {
      id: 1,
      first: { eng: 'Emergency', kor: '맵찌질' },
      second: { eng: 'Ignite', kor: '맵당당' },
      barColor: '#FF0000',
      name: 'ei',
    },
    {
      id: 2,
      first: { eng: 'Noodle', kor: '면파' },
      second: { eng: 'Ssal', kor: '밥파' },
      barColor: '#FFB800',
      name: 'ns',
    },
    {
      id: 3,
      first: { eng: 'Total', kor: '일반식' },
      second: { eng: 'Fitness', kor: '건강식' },
      barColor: '#11CF00',
      name: 'tf',
    },
    {
      id: 4,
      first: { eng: 'Jammine', kor: '잼민입맛' },
      second: { eng: 'Papa', kor: '아재입맛' },
      barColor: '#0029FF',
      name: 'jp',
    },
  ];

  const [ei, setEi] = useState(0);
  const [ns, setNs] = useState(0);
  const [tf, setTf] = useState(0);
  const [jp, setJp] = useState(0);

  // useState를 사용하여 바의 상태를 관리합니다.
  const [bars, setBars] = useState([
    { percentage: 0 },
    { percentage: 0 },
    { percentage: 0 },
    { percentage: 0 },
  ]);

  // 클릭 이벤트 핸들러를 정의합니다.
  const handleBarClick = (
    event: React.MouseEvent<HTMLDivElement>,
    index: number
  ) => {
    const bar = event.currentTarget;
    const clickedX = event.clientX - bar.getBoundingClientRect().left;
    const newPercentage = Math.min(
      100,
      Math.max(0, (clickedX / bar.offsetWidth) * 100)
    );

    // 해당 인덱스의 바의 상태를 업데이트합니다.
    setBars((prevBars) => {
      const newBars = [...prevBars];
      newBars[index].percentage = newPercentage;
      return newBars;
    });
  };

  const [isMukboInviteModalOpen, setIsMukboInviteModalOpen] =
    useState(false);
  const [isMukbotMakeModalOpen, setIsMukbotMakeModalOpen] =
    useState(false);
  const [
    isDeleteMukbotConfirmModalOpen,
    setIsDeleteMukbotConfirmModalOpen,
  ] = useState(false);
  const [isOutMukboConfirmModalOpen, setIsOutMukboConfirmModalOpen] =
    useState(false);

  const handleMukboInviteOpenModal = () => {
    setIsMukboInviteModalOpen(true);
  };
  const handleMukbotMakeOpenModal = () => {
    setIsMukbotMakeModalOpen(true);
  };

  const [deleteMukboId, setDeleteMukboId] = useState<number>(0);

  const handleDeleteMukbotConfirmModal = (mukboId: number) => {
    setIsDeleteMukbotConfirmModalOpen(true);
    setDeleteMukboId(mukboId);
  };

  const handleOutMukboConfirmModal = (mukboId: number) => {
    setIsOutMukboConfirmModalOpen(true);
    setDeleteMukboId(mukboId);
  };

  const handleCloseModal = () => {
    setIsDeleteMukbotConfirmModalOpen(false);
    setIsOutMukboConfirmModalOpen(false);
    setIsMukboInviteModalOpen(false);
    setIsMukbotMakeModalOpen(false);
    setInputList({
      ...inputList,
      mukbotName: '',
      email: '',
      nickname: '',
    });
    setEi(0);
    setNs(0);
    setTf(0);
    setJp(0);
    setBars([
      { percentage: 0 },
      { percentage: 0 },
      { percentage: 0 },
      { percentage: 0 },
    ]);
    setIsEmailValid(true);
    setIsNicknameValid(true);
    setIsMukbotnameValid(true);
    setErrorMessage('');
  };

  const [inputList, setInputList] = useState({
    nickname: '',
    email: '',
    mukbotName: '',
  });
  const { nickname, email, mukbotName } = inputList;
  // const inputValues = Object.values(inputList);

  const changeInputList = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    const { name, value } = e.target;
    setInputList({
      ...inputList,
      [name]: value,
    });
    console.log(email);
    if (name === 'email') {
      setErrorMessage('');
    }
  };

  const [isEmailValid, setIsEmailValid] = useState<boolean>(true);
  const checkEmail = () => {
    setIsEmailValid(checkEmailValidation(email));
  };

  const [isNicknameValid, setIsNicknameValid] =
    useState<boolean>(true);
  const checkNickName = () => {
    setIsNicknameValid(checkNicknameValidation(nickname));
  };

  const [isMukbotnameValid, setIsMukbotnameValid] =
    useState<boolean>(true);
  const checkMukbotname = () => {
    setIsMukbotnameValid(checkNicknameValidation(mukbotName));
  };

  // 먹보 조회 api
  const { data: groupUserList, isPending: isUserPending } = useQuery({
    queryKey: ['groupUserList'],
    queryFn: () => getGroupUserList(groupId),
  });

  useEffect(() => {
    if (!isUserPending && groupUserList) {
      console.log(
        'groupUserInfo.users.length: ',
        groupUserList.users.length
      );
      console.log(groupUserList);
    }
  }, [isUserPending, groupUserList]);

  // 먹봇 조회 api
  const { data: groupMukbotList, isPending: isMukbotPending } =
    useQuery({
      queryKey: ['groupMukbotList'],
      queryFn: () => getGroupMukbotList(groupId),
      enabled: !isMukbotMakeModalOpen,
    });

  useEffect(() => {
    if (!isMukbotPending && groupMukbotList) {
    }
  }, [isMukbotPending, groupMukbotList]);

  // 먹보 초대 이메일 검색 api
  const [errorMessage, setErrorMessage] = useState('');
  useEffect(() => {
    window.addEventListener('errorOccurred', handleErrorMessage);
    return () => {
      window.removeEventListener('errorOccurred', handleErrorMessage);
    };
  }, []);

  const handleErrorMessage = (event: Event) => {
    if (event instanceof CustomEvent) {
      // Axios 인터셉터에서 전달한 오류 메시지 처리
      setErrorMessage(event.detail.message);
    }
  };
  const { refetch } = useQuery({
    queryKey: ['mukboInfo'],
    queryFn: () => getEmailFind(email),
    enabled: false,
  });

  const handleEmailFind = () => {
    refetch().then((res) => {
      if (res) {
        console.log(email);
        // console.log('먹보 초대 이메일 검색: ', res);
      } else {
        console.log('응답 객체 또는 데이터가 존재하지 않습니다.');
      }
    });
  };

  // 먹보(인간) 초대 api
  const [linkMukbotId, setLinkMukbotid] = useState<number>(0);
  const handleLinkMukbot = (mukboId: number) => {
    setLinkMukbotid(mukboId);
    console.log('linkMukbotId: ', mukboId);
  };

  const handleMukboInvite = async () => {
    const mukboInviteData = {
      email,
      nickname,
      mukbotId: linkMukbotId,
    };
    try {
      await postMukboInvite(groupId, mukboInviteData);
      console.log('먹보 초대 데이터', mukboInviteData);
      setIsMukboInviteModalOpen(false);
      queryClient.invalidateQueries({
        queryKey: ['groupUserList'],
      });
      getGroupUserList(groupId);
      queryClient.invalidateQueries({
        queryKey: ['groupMukbotList'],
      });
      getGroupMukbotList(groupId);
      setInputList({
        ...inputList,
        email: '',
        nickname: '',
      });
    } catch (e) {
      console.error('먹봇 초대 오류:', e);
      // 오류
    }
  };

  // 먹봇 생성 api
  const handleMukbotMake = async (
    ei: number,
    ns: number,
    tf: number,
    jp: number
  ) => {
    // 각 MBTI 유형에 대해 새로운 값을 설정합니다.
    const newEi = Math.round(bars[0].percentage);
    const newNs = Math.round(bars[1].percentage);
    const newTf = Math.round(bars[2].percentage);
    const newJp = Math.round(bars[3].percentage);

    // 상태를 업데이트합니다.
    setEi(newEi);
    setNs(newNs);
    setTf(newTf);
    setJp(newJp);
    console.log('체크:', ei, ns, tf, jp);

    // mukbotMakeData 객체를 만들 때 이미 설정한 값을 사용합니다.
    const mukbotMakeData = {
      name: mukbotName,
      mbti: {
        ei: Math.round((newEi / 100) * 30),
        ns: Math.round((newNs / 100) * 30),
        tf: Math.round((newTf / 100) * 30),
        jp: Math.round((newJp / 100) * 30),
        mint: '15',
        pine: '15',
        die: '15',
      },
    };
    {
      newEi !== 0 &&
        newNs !== 0 &&
        newTf !== 0 &&
        newJp !== 0 &&
        (() => {
          try {
            console.log('처리중');
            postMukbotMake(groupId, mukbotMakeData);
            queryClient.invalidateQueries({
              queryKey: ['groupMukbotList'],
            });
            getGroupMukbotList(groupId);
            setIsMukbotMakeModalOpen(false);
            setInputList({
              ...inputList,
              mukbotName: '',
            });
            setEi(0);
            setNs(0);
            setTf(0);
            setJp(0);
            setBars([
              { percentage: 0 },
              { percentage: 0 },
              { percentage: 0 },
              { percentage: 0 },
            ]);
          } catch (error) {
            console.error('먹봇 생성 오류:', error);
            // 오류 처리
          }
        })();
    }
  };

  // 먹보/먹봇 추방/삭제 api
  const { mutate: mutateDeleteMukbos } = useMutation({
    mutationFn: (mukboId: number) => deleteMukbos(groupId, mukboId),
    onSuccess: () => {
      console.log('추방 성공');
      queryClient.invalidateQueries({
        queryKey: ['groupMukbotList'],
      });
      getGroupMukbotList(groupId);
      queryClient.invalidateQueries({
        queryKey: ['groupUserList'],
      });
      getGroupUserList(groupId);
    },
  });
  const handleDeleteMukbos = (mukboId: number) => {
    mutateDeleteMukbos(mukboId);
    setIsDeleteMukbotConfirmModalOpen(false);
    setIsOutMukboConfirmModalOpen(false);
  };

  return (
    <section className={styles.memberSection}>
      <span>구성원</span>
      <SubMemberArticle
        articleName="먹보"
        modalButton="+ 초대"
        clickEvent={handleMukboInviteOpenModal}
      >
        <div className={styles.memberCardBox}>
          {/* {if groupUserList} */}
          {groupUserList &&
            groupUserList.users.length > 0 &&
            groupUserList.users.map(
              (user: {
                name: string;
                mukBTI: string;
                mukboId: number;
              }) =>
                user.mukboId == myMukboId ? (
                  <SubMemberCard
                    articleName="먹보"
                    memberName={user.name}
                    memberMBTI={user.mukBTI}
                    buttonName="나"
                    clickEvent={() =>
                      handleDeleteMukbotConfirmModal(user.mukboId)
                    }
                  />
                ) : (
                  <SubMemberCard
                    articleName="먹보"
                    memberName={user.name}
                    memberMBTI={user.mukBTI}
                    buttonName="추방"
                    clickEvent={() =>
                      handleDeleteMukbotConfirmModal(user.mukboId)
                    }
                  />
                )
            )}
        </div>
      </SubMemberArticle>

      {/* 먹봇 추방 확인 모달 */}
      {isOutMukboConfirmModalOpen && (
        <Modal clickEvent={handleCloseModal}>
          <ConfirmModal
            content="정말로 먹보를 추방하시겠습니까?"
            yesEvent={() => handleDeleteMukbos(deleteMukboId)}
            noEvent={() => {
              setIsOutMukboConfirmModalOpen(false);
            }}
          />
        </Modal>
      )}

      {/* 먹보 초대 모달 */}
      {isMukboInviteModalOpen && (
        <Modal clickEvent={handleCloseModal}>
          <div className={styles.modal}>
            <header>먹보 초대</header>
            <article className={styles.mukboModalArticle}>
              <div className={styles.emailInput}>
                <Input
                  title="이메일"
                  info="example@naver.com"
                  inputName="email"
                  inputValue={email}
                  onChange={changeInputList}
                  inputType="text"
                  inputWidth="shortInput"
                  onBlur={checkEmail}
                  isInputValid={isEmailValid}
                  errorMessage="이메일 형식이 잘못되었습니다."
                />
                <img
                  className={styles.finder}
                  onClick={handleEmailFind}
                  src={finder}
                  alt="finder"
                />
              </div>
              <Input
                title="닉네임"
                info="2~10자 (한글, 영어(대/소), 숫자)"
                inputName="nickname"
                inputValue={nickname}
                onChange={changeInputList}
                inputType="text"
                inputWidth="shortInput"
                onBlur={checkNickName}
                isInputValid={isNicknameValid}
                errorMessage="닉네임 형식이 잘못되었습니다."
              />
              {errorMessage && (
                <div className={styles.checkMessage}>
                  {errorMessage}
                </div>
              )}
              <SubMemberArticle
                articleName="먹봇"
                modalButton=""
                clickEvent={handleMukbotMakeOpenModal}
              >
                <div className={styles.memberCardBox}>
                  {groupMukbotList &&
                    groupMukbotList.users.length > 0 &&
                    groupMukbotList.users.map(
                      (user: {
                        name: string;
                        mukBTI: string;
                        mukboId: number;
                      }) => (
                        <SubMemberCard
                          articleName="먹봇 연결"
                          memberName={user.name}
                          memberMBTI={user.mukBTI}
                          buttonName="링크"
                          clickEvent={() =>
                            handleLinkMukbot(user.mukboId)
                          }
                        />
                      )
                    )}
                  {!groupMukbotList && <div>로딩 중...</div>}
                  {groupMukbotList &&
                    groupMukbotList.users &&
                    groupMukbotList.users.length === 0 && (
                      <span>링크할 먹봇이 없어요.</span>
                    )}
                </div>
              </SubMemberArticle>
            </article>

            <button
              onClick={handleMukboInvite}
              className={buttonStyles.miniRoundedButton}
              // 닉네임, 이메일 유효X
              disabled={
                !isNicknameValid ||
                !isEmailValid ||
                !nickname ||
                !email
              }
            >
              확인
            </button>
          </div>
        </Modal>
      )}
      <SubMemberArticle
        articleName="먹봇"
        modalButton="+ 생성"
        clickEvent={handleMukbotMakeOpenModal}
      >
        <div className={styles.memberCardBox}>
          {groupMukbotList &&
            groupMukbotList.users.length > 0 &&
            groupMukbotList.users.map(
              (user: {
                name: string;
                mukBTI: string;
                mukboId: number;
              }) => (
                <SubMemberCard
                  articleName="먹봇"
                  memberName={user.name}
                  memberMBTI={user.mukBTI}
                  buttonName="삭제"
                  // clickEvent={() => handleDeleteMukbos(user.mukboId)}
                  clickEvent={() =>
                    handleOutMukboConfirmModal(user.mukboId)
                  }
                />
              )
            )}
          {!groupMukbotList && <div>로딩 중...</div>}
          {groupMukbotList &&
            groupMukbotList.users &&
            groupMukbotList.users.length === 0 && (
              <span>
                미가입 사용자는 <br />
                먹봇으로 생성해보세요.
              </span>
            )}
        </div>
      </SubMemberArticle>

      {/* 먹봇 삭제 확인 모달 */}
      {isDeleteMukbotConfirmModalOpen && (
        <Modal clickEvent={handleCloseModal}>
          <ConfirmModal
            content="정말로 먹봇을 삭제하시겠습니까?"
            yesEvent={() => handleDeleteMukbos(deleteMukboId)}
            noEvent={() => {
              setIsDeleteMukbotConfirmModalOpen(false);
            }}
          />
        </Modal>
      )}
      {/* 먹봇 생성 모달 */}
      {isMukbotMakeModalOpen && (
        <Modal clickEvent={handleCloseModal}>
          <div className={styles.modal}>
            <header>먹봇 생성</header>
            <article className={styles.mukbotModalArticle}>
              <Input
                title="이름"
                info="2~10자 (한글, 영어(대/소), 숫자)"
                inputName="mukbotName"
                inputValue={mukbotName}
                onChange={changeInputList}
                inputType="text"
                inputWidth="shortInput"
                onBlur={checkMukbotname}
                isInputValid={isMukbotnameValid}
                errorMessage="이름 형식이 잘못되었습니다."
              />
              {errorMessage && (
                <div className={styles.checkMessage}>
                  {errorMessage}
                </div>
              )}
              <div className={styles.mukbotMBTI}>
                <label>먹BTI</label>
                <span>
                  {Math.round(bars[0].percentage) !== 0 &&
                  Math.round(bars[1].percentage) !== 0 &&
                  Math.round(bars[2].percentage) !== 0 &&
                  Math.round(bars[3].percentage) !== 0 ? (
                    <>
                      {Math.round(bars[0].percentage) <= 50
                        ? 'E'
                        : 'I'}
                      {Math.round(bars[1].percentage) <= 50
                        ? 'N'
                        : 'S'}
                      {Math.round(bars[2].percentage) <= 50
                        ? 'T'
                        : 'F'}
                      {Math.round(bars[3].percentage) <= 50
                        ? 'J'
                        : 'P'}
                    </>
                  ) : (
                    '더 가까운 쪽으로 선택해주세요.'
                  )}
                </span>
              </div>
              <div className={styles.mbtiDetail}>
                {bars.map((bar, index) => {
                  // 해당 바의 mbtiOptions 가져오기
                  const mbtiOptions = MBTI_OPTIONS[index];
                  return (
                    <div key={index} className={styles.barWrapper}>
                      <div className={styles.mbtiNameBox}>
                        <span className={styles.mbtiNameEng}>
                          {mbtiOptions.first.eng}
                        </span>
                        <span className={styles.mbtiNameKor}>
                          {mbtiOptions.first.kor}
                        </span>
                      </div>
                      <div
                        key={index}
                        className={styles.bar}
                        onClick={(event) => {
                          handleBarClick(event, index);
                        }}
                      >
                        <div
                          className={styles.progress}
                          style={{
                            width: `${bar.percentage}%`,
                            backgroundColor: mbtiOptions.barColor,
                          }}
                        ></div>
                        <div
                          className={styles.indicator}
                          style={{ left: `${bar.percentage}%` }}
                        >
                          {Math.round(bar.percentage)}
                        </div>
                      </div>
                      <div className={styles.mbtiNameBox}>
                        <span className={styles.mbtiNameEng}>
                          {mbtiOptions.second.eng}
                        </span>
                        <span className={styles.mbtiNameKor}>
                          {mbtiOptions.second.kor}
                        </span>
                      </div>
                    </div>
                  );
                })}
              </div>
            </article>

            <button
              onClick={() => handleMukbotMake(ei, ns, tf, jp)}
              className={buttonStyles.miniRoundedButton}
              disabled={
                !isMukbotnameValid ||
                !mukbotName ||
                (bars.length > 0 &&
                  bars.some((bar) => !bar.percentage))
              }
            >
              추가
            </button>
          </div>
        </Modal>
      )}
    </section>
  );
}

export default MemberSection;
