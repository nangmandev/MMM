import { useState } from 'react';
import Input from '../components/common/Input';
import styles from '../styles/userPage/UserPage.module.css';
import buttonStyles from '../styles/common/Buttons.module.css';
import inputStyles from '../styles/common/Input.module.css';
import Button from '../components/common/Button';
import subLogo from '../assets/images/subLogo.png';
import closedEye from '../assets/images/closedEye.png';
import openedEye from '../assets/images/openedEye.png';
import { Link, useNavigate } from 'react-router-dom';
import {
  checkEmailValidation,
  checkNicknameValidation,
  checkPasswordValidation,
} from '../utils/validation';
import {
  getNicknameValidate,
  postEmailCode,
  postSendEmail,
  postSignup,
} from '../api/userApi';
import { useQuery } from 'react-query';
import { useMutation } from '@tanstack/react-query';
import ErrorMessage from '../components/common/ErrorMessage';

function SignupPage() {
  const navigate = useNavigate();
  const [inputList, setInputList] = useState({
    nickname: '',
    email: '',
    authNum: '',
    password: '',
    passwordConfirm: '',
  });
  const { nickname, email, authNum, password, passwordConfirm } =
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
    if (name === 'email') {
      setIsEmailButtonClicked(false);
    }
    if (name === 'authNum') {
      setIsEmailCodeValid('');
    }
  };
  const [isEmailButtonClicked, setIsEmailButtonClicked] =
    useState<boolean>(false);
  const [isPasswordOpened, setIsPasswordOpened] =
    useState<boolean>(false);

  const [isNicknameValid, setIsNicknameValid] =
    useState<boolean>(true);
  const checkNickName = () => {
    setIsNicknameValid(checkNicknameValidation(nickname));
  };

  // 닉네임 중복 확인 api
  const { refetch } = useQuery(
    ['nicknameValidate'],
    () => getNicknameValidate(nickname),
    { enabled: false }
  );
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

  const [isEmailValid, setIsEmailValid] = useState<boolean>(true);
  const checkEmail = () => {
    setIsEmailValid(checkEmailValidation(email));
  };

  const [isPasswordValid, setIsPasswordValid] =
    useState<boolean>(true);

  const checkPassword = () => {
    setIsPasswordValid(checkPasswordValidation(password));
  };

  const [isPasswordConfirmValid, setIsPasswordConfirmValid] =
    useState<boolean>(true);

  const checkPasswordConfirm = () => {
    if (password !== passwordConfirm)
      setIsPasswordConfirmValid(false);
    else setIsPasswordConfirmValid(true);
  };

  const togglePassword = () => {
    setIsPasswordOpened(!isPasswordOpened);
  };

  // 이메일 인증코드 발송 api
  const { mutate: mutateSendEmail } = useMutation({
    mutationFn: postSendEmail,
  });
  const handleSendEmail = () => {
    setIsEmailButtonClicked(!isEmailButtonClicked);
    const sendEmailData = {
      email,
    };
    mutateSendEmail(sendEmailData);
  };

  const [isEmailCodeValid, setIsEmailCodeValid] = useState<string>();
  // 이메일 인증코드 확인 api
  const { mutate: mutateEmailCode } = useMutation({
    mutationFn: postEmailCode,
    onSuccess: () => {
      setIsEmailCodeValid('ok');
    },
    onError: () => {
      setIsEmailCodeValid('no')
    }
  });
  const handleEmailCode = () => {
    const emailCodeData = {
      email,
      authNum,
    };
    mutateEmailCode(emailCodeData);
  };

  
  // 회원가입 api
  const { mutate: mutateSignup } = useMutation({
    mutationFn: postSignup,
    onSuccess: () => {
      navigate('/login')
    },
  });
  const handleSignup = () => {
    const signupData = {
      email,
      password,
      passwordConfirm,
      nickname,
    };
    mutateSignup(signupData);
  };

  return (
    <div className={styles.wrapper}>
      <Link to="/">
        <img className={styles.subLogo} src={subLogo} alt="" />
      </Link>
      {/* <div className="userTitle">SIGN UP</div> */}
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

        <div>
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
          <Button
            clickEvent={isEmailValid ? handleSendEmail : () => {}}
            buttonName="인증"
            disabledEvent={isEmailButtonClicked}
          />
        </div>
        {isEmailButtonClicked && isEmailValid && (
          <div className={inputStyles.inputContainer}>
            <label></label>
            <div className={inputStyles.inputBox}>
              <input
                type="text"
                name="authNum"
                value={authNum}
                onChange={changeInputList}
                className={inputStyles.shortInput}
                placeholder="인증번호"
                
              />
              <span></span>
              {isEmailCodeValid == 'no' && <ErrorMessage errorMessage='인증번호가 일치하지 않습니다.' />}
            </div>
            <Button
              disabledEvent={false}
              clickEvent={handleEmailCode}
              buttonName={isEmailCodeValid == 'ok' ? '✓' : '확인'}
            />
          </div>
        )}

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
        <div className={styles.inputPassword}>
          <Input
            title={'비밀번호\n확인'}
            info=""
            inputName="passwordConfirm"
            inputValue={passwordConfirm}
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
      <button
        onClick={handleSignup}
        className={buttonStyles.userButton}
        // 닉네임 중복 || 비번 같지X || 닉네임, 비번, 인증번호 유효X || inputList 하나라도 비어있음
        disabled={
          isNicknameDuplicated ||
          !isPasswordConfirmValid ||
          !isNicknameValid ||
          !isPasswordValid ||
          isEmailCodeValid !== 'ok' ||
          inputValues.some((val) => val === '')
        }
      >
        완료
      </button>
    </div>
  );
}

export default SignupPage;
