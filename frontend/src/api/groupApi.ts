import instance from './axios';

export async function getGroupInfo() {
  try {
    const res = await instance.get('/groups', {});
    console.log(res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

export async function postGroupInfo(name: string) {
  const data = { name };
  console.log('data:', data);
  const blob = new Blob([JSON.stringify(data)], {
    type: 'application/json',
  });
  const formData = new FormData();
  formData.append('data', blob);

  try {
    console.log('formData:', formData);
    const res = await instance.post('/groups', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    console.log('결과는!', res);
    return res;
  } catch (e) {
    console.log(e);
  }
}

export async function getLog(groupId: number) {
  try {
    console.log('그룹아이디:', groupId);
    const res = await instance.get(`/groups/${groupId}/log`, {
      params: {
        page: 0,
        size: 15,
      },
      headers: {
        'Content-Type': 'application/json',
      },
    });
    console.log(res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

interface groupInfoType {
  groupId: number;
  groupName: string;
}
export async function putGroupName({
  groupId,
  groupName,
}: groupInfoType) {
  const data = { name: groupName };
  try {
    console.log('그룹아이디:', groupId);
    const res = await instance.put(`/groups/${groupId}/name`, data);
    console.log(res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

interface groupImgType {
  groupId: number;
  groupImg: File;
}
export async function modifyGroupImage({
  groupId,
  groupImg,
}: groupImgType) {
  const formData = new FormData();
  formData.append('image', groupImg);
  try {
    const res = await instance.post(
      `/groups/${groupId}/image`,
      formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      }
    );
    console.log(res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

export async function deleteGroup(groupId: number) {
  try {
    const res = await instance.delete(`/groups/${groupId}/exit`);
    console.log('삭제결과:', res)
    return res.data;
  } catch (e) {
    console.log('삭제에러:',e);
  }
}

interface mbtiType {
  die: number;
  ei: number;
  jp: number;
  mint: number;
  ns: number;
  pine: number;
  tf: number;
}

interface userType {
  name: string;
  mukBTI: string;
  mukboId: number;
  type: string;
  mbti: mbtiType;
}

interface todayMemberMbtiType {
  groupId: number;
  todayMemberList: userType[];
}

export async function postTodayMemberMbti({
  groupId,
  todayMemberList,
}: todayMemberMbtiType) {
  const mukboIdsArray = todayMemberList.map((obj) => obj.mukboId);
  const data = { mukbos: mukboIdsArray };
  try {
    const res = await instance.post(`/groups/${groupId}/mbti`, data);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}
