import axios from 'axios';

const { VITE_API_DEV } = import.meta.env;

// 로그인 오류 메시지
const errorEvent = new CustomEvent('errorOccurred', {
  detail: { message: '' },
});

const instance = axios.create({
  baseURL: VITE_API_DEV,
  withCredentials: true,
});

instance.interceptors.request.use(
  (config) => {
    const userDataString = localStorage.getItem('userData');
    if (userDataString !== null) {
      const userData = JSON.parse(userDataString);
      const accessToken = userData.state.accessToken;
      console.log('accesstoken: ', accessToken);
      config.headers['Authorization'] = accessToken;
    } else {
      console.log('userData가 null입니다.');
    }

    return config;
  },
  (error) => {
    console.log('config 에러: ', error);
    return Promise.reject(error);
  }
);
instance.interceptors.response.use(
  async (response) => {
    return response;
  },
  async (error) => {
    console.log(error);
    console.log('errorName: ', error.response.data.errorName);

    // // 토큰 만료 시 재발급
    // if (
    //   error.response &&
    //   error.response.data.errorName === 'TOKEN_EXPIRED'
    // ) {
    //   const refreshTokenData = await getRefreshToken();
    //   console.log('리프레시 데이터', refreshTokenData)
    //   console.log('error.response: ', error.response);
    //   if (refreshTokenData) {
    //     const newAccessToken = refreshTokenData.accessToken;
    //     error.config.headers.Authorization = newAccessToken;
    //     return instance(error.config);
    //   } else {
    //     console.log('axios.ts 토큰 재발급 실패');
    //   }
    // }
    // console.log('response에러 :', error);


    // 로그인 시 오류
    if (
      error.response &&
      error.response.data.errorName === 'INVALID_USER'
    ) {
      // 'INVALID_USER' 오류가 발생하면 오류 메시지 이벤트 발생
      errorEvent.detail.message = '아이디/비밀번호를 확인해주세요.';
      window.dispatchEvent(errorEvent);
    }

    // 회원정보 수정 시 오류
    // 기존 닉네임과 일치
    if (
      error.response &&
      error.response.data.errorName === 'EXIST_NICKNAME'
    ) {
      // 'PASSWORD_CONFLICT' 오류가 발생하면 오류 메시지 이벤트 발생
      errorEvent.detail.message = '이미 사용하고 있는 닉네임입니다.';
      window.dispatchEvent(errorEvent);
    }

    // 기존 패스워드와 일치하지 않음
    if (
      error.response &&
      error.response.data.errorName === 'PASSWORD_CONFLICT'
    ) {
      // 'PASSWORD_CONFLICT' 오류가 발생하면 오류 메시지 이벤트 발생
      errorEvent.detail.message =
        '기존 패스워드와 일치하지 않습니다.';
      window.dispatchEvent(errorEvent);
    }

    // 먹보 초대 존재하지 않는 유저
    if (
      error.response &&
      error.response.data.errorName === 'USER_NOT_FOUND'
    ) {
      // 'USER_NOT_FOUND' 오류가 발생하면 오류 메시지 이벤트 발생
      errorEvent.detail.message = '존재하지 않는 사용자입니다.';
      window.dispatchEvent(errorEvent);
    }

    // 이미 그룹에 속해 있는 유저
    if (
      error.response &&
      error.response.data.errorName === 'DUPLICATE_ERROR'
    ) {
      // 'USER_NOT_FOUND' 오류가 발생하면 오류 메시지 이벤트 발생
      errorEvent.detail.message = '이미 먹그룹에 속해 있는 사용자입니다.';
      window.dispatchEvent(errorEvent);
    }

    

    return Promise.reject(error);
  }
);

// // 토큰 재발급
// const getRefreshToken = async () => {
//   //
//   const userDataString = localStorage.getItem('userData');

//   if (userDataString !== null) {
//     // JSON 형식으로 파싱하기
//     const userData = JSON.parse(userDataString);

//     // refreshToken에 접근
//     const refreshToken = userData.state.refreshToken;
//     console.log('refreshToken:', refreshToken);
//     try {
//       const res = await instance.get('/users/reissue', {
//         headers: {
//           Authorization: refreshToken,
//         },
//       });
//       console.log('토큰 재발급 성공:', res);
//       return res.data;

//       //
//     } catch (e) {
//       console.log('토큰 재발급 실패:', e);
//       console.log('토큰 재발급 실패:', refreshToken);
//       // 토큰 재발급 실패 시 오류 상태 반환
//       return { error: e };
//     }
//   } else {
//     console.error('userData가 null입니다.');
//   }
// };

export default instance;
