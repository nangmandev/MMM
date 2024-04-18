import { useEffect, useState } from 'react';
import Input from '../components/common/Input';
import buttonStyles from '../styles/common/Buttons.module.css';
import styles from '../styles/userPage/UserPage.module.css';
import subLogo from '../assets/images/subLogo.png';
import closedEye from '../assets/images/closedEye.png';
import openedEye from '../assets/images/openedEye.png';
import { Link, useNavigate } from 'react-router-dom';
import {
  checkEmailValidation,
  checkPasswordValidation,
} from '../utils/validation';
import { postLogin } from '../api/userApi';
import { useMutation } from '@tanstack/react-query';
import userStore from '../stores/userStore';

function LoginPage() {
  const { setAccessToken, setRefreshToken, setIsLogin, setIsRecorded } = userStore();
  const navigate = useNavigate();
  const [inputList, setInputList] = useState({
    email: '',
    password: '',
  });
  const { email, password } = inputList;
  const inputValues = Object.values(inputList);

  const changeInputList = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    const { name, value } = e.target;
    setInputList({
      ...inputList,
      [name]: value,
    });
    console.log(name, value);
    setErrorMessage('');
  };
  const [isPasswordOpened, setIsPasswordOpened] =
    useState<boolean>(false);

  const togglePassword = () => {
    setIsPasswordOpened(!isPasswordOpened);
  };

  const [isPasswordValid, setIsPasswordValid] =
    useState<boolean>(true);

  const checkPassword = () => {
    setIsPasswordValid(checkPasswordValidation(password));
  };

  const [isEmailValid, setIsEmailValid] = useState<boolean>(true);
  const checkEmail = () => {
    setIsEmailValid(checkEmailValidation(email));
  };

  // 로그인 api
  // 로그인 오류 메시지
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

  const { mutate: mutateLogin } = useMutation({
    mutationFn: postLogin,
    onSuccess: (data) => {
      setAccessToken(data.accessToken);
      setRefreshToken(data.refreshToken);
      setIsLogin(true);
      setIsRecorded(data.isRecorded);
      console.log('로그인 성공');
      localStorage.setItem('refreshToken', data.refreshToken);
      if (!data.isRecorded) {
        navigate('/mbti/0');
      } else {
        navigate('/');
      }
    },
  });

  const handleLogin = () => {
    const loginData = {
      email,
      password,
    };
    mutateLogin(loginData);
  };

  return (
    <div className={styles.wrapper}>
      <Link to="/">
        <img className={styles.subLogo} src={subLogo} alt="" />
      </Link>
      <section>
        <Input
          title="이메일"
          info="example@naver.com"
          inputName="email"
          inputValue={email}
          onChange={changeInputList}
          inputType="text"
          inputWidth="longInput"
          onBlur={checkEmail}
          isInputValid={isEmailValid}
          errorMessage="이메일 형식이 잘못되었습니다."
        />
        <div className={styles.inputPassword}>
          <Input
            title="비밀번호"
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
      </section>
      {/* <ErrorMessage
        errorFontSize="bigErrorMessage"
        errorTarget="닉네임"
      /> */}
      {errorMessage && (
        <div className={styles.checkMessage}>{errorMessage}</div>
      )}
      <button
        onClick={handleLogin}
        className={buttonStyles.userButton}
        type="button"
        disabled={
          !isEmailValid ||
          !isPasswordValid ||
          inputValues.some((val) => val === '')
        }
      >
        로그인
      </button>
      <span>
        <Link to="/signup">기존 회원이 아니신가요?</Link>
      </span>
    </div>
  );
}

export default LoginPage;
