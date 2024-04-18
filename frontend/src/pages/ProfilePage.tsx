import { useEffect, useState } from 'react';
import Input from '../components/common/Input';
import styles from '../styles/userPage/UserPage.module.css';
import buttonStyles from '../styles/common/Buttons.module.css';
import userTitleStyles from '../styles/common/UserTitle.module.css';
import Button from '../components/common/Button';
import closedEye from '../assets/images/closedEye.png';
import openedEye from '../assets/images/openedEye.png';
import { useNavigate } from 'react-router-dom';
import {
  checkNicknameValidation,
  checkPasswordValidation,
} from '../utils/validation';
import {
  deleteLogout,
  getNicknameValidate,
  postEditProfile,
  getProfile,
} from '../api/userApi';
import { useQuery } from '@tanstack/react-query';
import { useMutation } from '@tanstack/react-query';
import userStore from '../stores/userStore';

function ProfilePage() {
  const { setAccessToken, setRefreshToken, setIsLogin } = userStore();
  const navigate = useNavigate();
  const [inputList, setInputList] = useState({
    nickname: '',
    password: '',
    newPassword: '',
    newPasswordConfirm: '',
  });
  const { nickname, password, newPassword, newPasswordConfirm } =
    inputList;
  const inputValues = Object.values(inputList);

  const changeInputList = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    const { name, value } = e.target;
    setInputList({
      ...inputList,
      [name]: value,
    });
    // console.log(name, value);
    // console.log('inputList:', inputList);
    if (name === 'nickname') {
      setIsNicknameDuplicated(undefined);
    }
    if (name === 'password') {
      setErrorMessage('');
    }
  };
  const [isPasswordOpened, setIsPasswordOpened] =
    useState<boolean>(false);

  const togglePassword = () => {
    setIsPasswordOpened(!isPasswordOpened);
  };

  const [isNicknameValid, setIsNicknameValid] =
    useState<boolean>(true);
  const checkNickName = () => {
    setIsNicknameValid(checkNicknameValidation(nickname));
  };

  // 닉네임 중복 확인 api
  const { refetch } = useQuery({
    queryKey: ['nicknameValidate'],
    queryFn: () => getNicknameValidate(nickname),
    enabled: false,
  });
  const [isNicknameDuplicated, setIsNicknameDuplicated] =
    useState<boolean>();

  const handleValidateNickname = () => {
    refetch().then((res) => {
      if (res && res.data) {
        console.log('닉네임 중복인가: ', res.data.data);
        setIsNicknameDuplicated(res.data.data);
      } else {
        console.log('응답 객체 또는 데이터가 존재하지 않습니다.');
      }
    });
  };

  const [isPasswordValid, setIsPasswordValid] =
    useState<boolean>(true);
  const checkPassword = () => {
    setIsPasswordValid(checkPasswordValidation(password));
  };

  const [isNewPasswordValid, setIsNewPasswordValid] =
    useState<boolean>(true);
  const checkNewPassword = () => {
    setIsNewPasswordValid(checkPasswordValidation(newPassword));
  };

  const [isPasswordConfirmValid, setIsPasswordConfirmValid] =
    useState<boolean>(true);

  const checkPasswordConfirm = () => {
    if (newPassword !== newPasswordConfirm)
      setIsPasswordConfirmValid(false);
    else setIsPasswordConfirmValid(true);
  };

  // 개인정보 수정 api
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

  const { mutate: mutateProfile } = useMutation({
    mutationFn: postEditProfile,
    onSuccess: (data) => {
      console.log('개인정보 수정 성공:', data);
      if (errorMessage === '') {
        navigate('/');
      }
    },
    onError: (error) => {
      console.error('onError:', errorMessage);
      console.error('개인정보 수정 실패:', error);
    },
  });
  const handleEditProfile = () => {
    const editProfileData = {
      password,
      newPassword,
      newPasswordConfirm,
      nickname,
    };
    mutateProfile(editProfileData);
  };

  // 로그아웃 api
  const { mutate: mutateLogout } = useMutation({
    mutationFn: deleteLogout,
    onSuccess: () => {
      navigate('/landing');
      setAccessToken('');
      setRefreshToken('');
      setIsLogin(false);
      console.log('로그아웃 성공');
    },
  });
  const handleLogout = () => {
    mutateLogout();
  };

  // 현재 사용자 정보 조회 api
  const { data: userInfo, isPending } = useQuery({
    queryKey: ['userInfo'],
    queryFn: () => getProfile(),
  });

  useEffect(() => {
    if (!isPending && userInfo) {
      setInputList({
        ...inputList,
        nickname: userInfo.nickname,
      });
    }
  }, [isPending, userInfo]);

  return (
    <div className={styles.wrapper}>
      <div className={userTitleStyles.userTitle}>PROFILE</div>
      <section>
        <div>
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
          <Button
            clickEvent={handleValidateNickname}
            buttonName={
              isNicknameDuplicated == false && isNicknameValid
                ? '✓'
                : '확인'
            }
            disabledEvent={isNicknameDuplicated == false}
          />
        </div>

        <div className={styles.inputPassword}>
          <Input
            title={'기존\n비밀번호'}
            info={
              '8~20자\n(영어(대/소), 숫자, 특수문자 각 1글자 이상)'
            }
            inputName="password"
            inputValue={password}
            onChange={changeInputList}
            inputType={isPasswordOpened ? 'text' : 'password'}
            inputWidth="longInput"
            onBlur={checkPassword}
            isInputValid={isPasswordValid}
            errorMessage="비밀번호 형식이 잘못되었습니다."
          />
          {isPasswordOpened ? (
            <img onClick={togglePassword} src={openedEye} alt="" />
          ) : (
            <img onClick={togglePassword} src={closedEye} alt="" />
          )}
        </div>
        <div className={styles.inputPassword}>
          <Input
            title="비밀번호"
            info={
              '8~20자\n(영어(대/소), 숫자, 특수문자 각 1글자 이상)'
            }
            inputName="newPassword"
            inputValue={newPassword}
            onChange={changeInputList}
            inputType={isPasswordOpened ? 'text' : 'password'}
            inputWidth="longInput"
            onBlur={checkNewPassword}
            isInputValid={isNewPasswordValid}
            errorMessage="비밀번호 형식이 잘못되었습니다."
          />
          {isPasswordOpened ? (
            <img onClick={togglePassword} src={openedEye} alt="" />
          ) : (
            <img onClick={togglePassword} src={closedEye} alt="" />
          )}
        </div>
        <div className={styles.inputPassword}>
          <Input
            title={'비밀번호\n확인'}
            info=""
            inputName="newPasswordConfirm"
            inputValue={newPasswordConfirm}
            onChange={changeInputList}
            inputType={isPasswordOpened ? 'text' : 'password'}
            inputWidth="longInput"
            onBlur={checkPasswordConfirm}
            isInputValid={isPasswordConfirmValid}
            errorMessage="비밀번호를 다시 확인해주세요."
          />
          {isPasswordOpened ? (
            <img onClick={togglePassword} src={openedEye} alt="" />
          ) : (
            <img onClick={togglePassword} src={closedEye} alt="" />
          )}
        </div>
        <span className={styles.checkMessage}>
          {isNicknameDuplicated === true
            ? '이미 사용하고 있는 닉네임입니다.'
            : ''}
        </span>
      </section>
      {errorMessage && (
        <div className={styles.checkMessage}>{errorMessage}</div>
      )}
      <button
        onClick={handleEditProfile}
        className={buttonStyles.userButton}
        // 닉네임 중복 || 비번 같지X || 닉네임, 비번 유효X || inputList 하나라도 비어있음
        disabled={
          isNicknameDuplicated == null ||
          isNicknameDuplicated ||
          !isPasswordConfirmValid ||
          !isNicknameValid ||
          !isPasswordValid ||
          inputValues.some((val) => val === '')
        }
      >
        완료
      </button>
      <span className={styles.logout} onClick={handleLogout}>
        로그아웃
      </span>
    </div>
  );
}

export default ProfilePage;
