import instance from './axios';

interface mbtiType {
  ei: number;
  ns: number;
  tf: number;
  jp: number;
  mint: number;
  pine: number;
  die: number;
}

export async function getRandomFoodList() {
  try {
    const res = await instance.get('/recommend');
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

export async function getRecentRecommendFood(groupId: number) {
  console.log('여기에 그룹아이디:', groupId);
  try {
    const res = await instance.get(`/mukus/groups/${groupId}/recent`);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

export async function getMainRecommendFood(
  groupId: number,
  mbti: mbtiType
) {
  try {
    const res = await instance.get(`/recommend/groups/${groupId}`, {
      params: {
        ei: mbti.ei,
        ns: mbti.ns,
        tf: mbti.tf,
        jp: mbti.jp,
      },
    });
    return res.data.foods;
  } catch (e) {
    console.log(e);
  }
}

export async function getNewRecommendFood(groupId: number) {
  try {
    const res = await instance.get(
      `/recommend/groups/${groupId}/new`
    );
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

export async function getWeatherRecommendFood(
  latitude: string,
  longitude: string
) {
  const data = {
    latitude,
    longitude,
  };
  try {
    const res = await instance.get('/recommend/weather', {
      params: data,
    });
    return res.data;
  } catch (e) {
    console.log(e);
  }
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

export async function postTodayMember({
  groupId,
  todayMemberList,
}: todayMemberMbtiType) {
  const mukboIdsArray = todayMemberList.map((obj) => obj.mukboId);
  const data = { nowMukbos: mukboIdsArray };
  try {
    const res = await instance.post(
      `/recommend/groups/${groupId}/mukbos-now`,
      data
    );
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

interface recordType {
  isSelectedId: number;
  groupId: number;
}
export async function postRecord({
  isSelectedId,
  groupId,
}: recordType) {
  const data = {
    recommendFoodId: isSelectedId,
  };
  try {
    const res = await instance.post(
      `/mukus/groups/${groupId}/recent`,
      data
    );
    console.log('기록결과:', res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}
