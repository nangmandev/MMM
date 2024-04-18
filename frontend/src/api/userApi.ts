import instance from './axios';

// 닉네임 중복 확인
export const getNicknameValidate = async (nickname: string) => {
  try {
    const res = await instance.get(`/users/${nickname}`);
    console.log('닉네임 res:', res);
    return res;
  } catch (e) {
    console.log('닉네임 중복 확인 실패:', e);
  }
};

// 이메일 인증 코드 발송
type SendEmailData = {
  email: string;
};
export const postSendEmail = async (sendEmailData: SendEmailData) => {
  try {
    const res = await instance.post(
      '/users/email/verification-request',
      sendEmailData,
      {}
    );
    console.log('이메일 인증코드 발송 성공:', res, sendEmailData);
  } catch (e) {
    console.log('이메일 인증코드 발송 실패:', e);
    throw e;
  }
};

// 이메일 인증 코드 확인
type EmailCodeData = {
  email: string;
  authNum: string;
};
export const postEmailCode = async (emailCodeData: EmailCodeData) => {
  try {
    const res = await instance.post(
      '/users/email/verification',
      emailCodeData,
      {}
    );
    console.log(
      '이메일 인증코드 확인 성공:',
      res.data,
      emailCodeData
    );
    return res.data;
  } catch (e) {
    console.log('이메일 인증코드 확인 실패:', e);
    throw e;
  }
};

// 회원가입
type SignupData = {
  email: string;
  password: string;
  passwordConfirm: string;
  nickname: string;
};
export const postSignup = async (signupData: SignupData) => {
  try {
    const res = await instance.post('/users/join', signupData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    console.log('회원가입 성공:', res, signupData);
  } catch (e) {
    console.log('회원가입 실패:', e);
  }
};

// 로그인
type LoginData = {
  email: string;
  password: string;
};
export const postLogin = async (loginData: LoginData) => {
  try {
    const res = await instance.post('/users/login', loginData, {});
    // console.log('userStore accessToken:', accessToken);
    console.log('로그인 데이터:', res, loginData);
    return res.data;
  } catch (e) {
    console.log('로그인 실패:', e);
    throw e;
  }
};

// 로그아웃
export const deleteLogout = async () => {
  try {
    const res = await instance.delete('/users/logout', {});
    // console.log('userStore accessToken:', accessToken);
    return res.data;
  } catch (e) {
    console.log('로그아웃 실패:', e);
    // console.log(localStorage.getItem('userData'))

    
    throw e;
  }
};

// 개인정보 수정
type EditProfileData = {
  nickname: string;
  password: string;
  newPassword: string;
  newPasswordConfirm: string;
};
export const postEditProfile = async (
  editProfileData: EditProfileData
) => {
  try {
    const res = await instance.put('/users', editProfileData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    console.log('개인정보 수정 데이터:', res, editProfileData);
    return res.data;
  } catch (e) {
    console.log('개인정보 수정 실패:', e);
    throw e;
  }
};

// 현재 사용자 정보 조회
export const getProfile = async () => {
  try {
    const res = await instance.get('/users', {
      // headers: {
      //   Authorization: accessToken,
      // },
    });
    // console.log('사용자 정보 조회 성공:', res);
    return res.data;
  } catch (e) {
    console.log('사용자 정보 조회 실패:', e);
  }
};

