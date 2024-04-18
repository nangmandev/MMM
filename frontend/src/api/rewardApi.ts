import instance from './axios';

export async function getReward(groupId: number) {
  try {
    const res = await instance.get(`/groups/${groupId}/badges`);
    console.log('먹적:',res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}

interface rewardType {
  groupId: number;
  selectedId: number | null;
}
export async function postTitleReward({groupId, selectedId}: rewardType) {
  const data = { badgeId: selectedId }
  try {
    const res = await instance.put(`/groups/${groupId}/badges`, data, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    console.log('먹적수정:',res);
    return res.data;
  } catch (e) {
    console.log(e);
  }
}