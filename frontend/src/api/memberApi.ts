import instance from './axios';

// 먹보 조회
export const getGroupUserList = async (groupId: number) => {
  try {
    const res = await instance.get(`/groups/${groupId}/users`, {});
    console.log('먹보 조회 res:', res);
    return res.data;
  } catch (e) {
    console.log('먹보 조회 실패:', e);
  }
};

// 먹봇 조회
export const getGroupMukbotList = async (groupId: number) => {
  try {
    const res = await instance.get(`/groups/${groupId}/mukbots`, {});
    console.log('먹봇 조회 res:', res);
    return res.data;
  } catch (e) {
    console.log('먹봇 조회 실패:', e);
  }
};

// 먹보 초대 이메일 검색
export const getEmailFind = async (email: string) => {
  try {
    const res = await instance.get(`/users/search?email=${email}`, {});
    console.log('먹보 초대 이메일 검색 res:', res);
    return res.data;
  } catch (e) {
    console.log('먹보 초대 이메일 검색 실패:', e);
  }
};

// 먹보(인간) 초대
type MukboInviteData = {
  email: string;
  nickname: string;
  mukbotId: number;
};
export const postMukboInvite = (groupId: number, mukboInviteData: MukboInviteData) => {
  return instance.post(
      `/groups/${groupId}/users`,
      mukboInviteData,
      {headers: {
        'Content-Type': 'application/json',
      },}
    );
  //   console.log('먹보(인간)초대 res:', res, mukboInviteData);
  // } catch (e) {
  //   console.log('먹보(인간)초대 e:', e);
  //   throw e;
  // }
};

// 먹봇 생성
type MukbotMakeData = {
  name: string,
  mbti: {
    ei: number,
    ns: number,
    tf: number,
    jp: number,
    mint: string,
    pine: string,
    die: string,
  },
};
export const postMukbotMake = async (groupId: number, mukbotMakeData: MukbotMakeData) => {
  try {
    const res = await instance.post(
      `/groups/${groupId}/mukbots`,
      mukbotMakeData,
      {headers: {
        'Content-Type': 'application/json',
      },}
    );
    console.log('먹봇 생성 res:', res, mukbotMakeData);
  } catch (e) {
    console.log('먹봇 생성 e:', e);
    throw e;
  }
};

// 먹보/먹봇 추방/삭제
export const deleteMukbos = async (groupId: number, mukboId: number) => {
  try {
    const res = await instance.delete(`/groups/${groupId}/mukbos/${mukboId}`, {});
    return res.data;
  } catch (e) {
    console.log('추방/삭제 에러:', e);
    throw e;
  }
};