import { create } from 'zustand';
import { persist } from 'zustand/middleware';

interface answerType {
  quizId: string;
  answerId: string;
}

interface mbtiType {
  ei: number;
  ns: number;
  tf: number;
  jp: number;
  mint: number;
  pine: number;
  die: number;
}

interface memberType {
  mukboId: number;
  type: string;
  name: string;
  mukBTI: string;
  mbti: mbtiType;
}

interface foodType {
  id: number;
  name: string;
  image: string;
  categoryId: number;
}

interface userStoreType {
  answerList: answerType[];
  updateAnswerList: (value: answerType[]) => void;
  accessToken: string;
  setAccessToken: (value: string) => void;
  refreshToken: string;
  setRefreshToken: (value: string) => void;
  isLogin: boolean;
  setIsLogin: (value: boolean) => void;
  isRecorded: boolean;
  setIsRecorded: (value: boolean) => void;
  mbtiKey: string;
  setMbtiKey: (value: string) => void;
  userMbti: mbtiType;
  setUserMbti: (value: mbtiType) => void;
  groupMbti: mbtiType;
  setGroupMbti: (value: mbtiType) => void;
  groupId: number;
  setGroupId: (value: number) => void;
  isSolo: boolean;
  setIsSolo: (value: boolean) => void;
  isCreateModalOpen: boolean;
  setIsCreateModalOpen: (value: boolean) => void;
  todayMemberList: memberType[];
  setTodayMemberList: (value: memberType[]) => void;
  nextMemberList: memberType[];
  setNextMemberList: (value: memberType[]) => void;
  myMukboId: number;
  setMyMukboId: (value: number) => void;
  recommendFoodList: foodType[];
  setRecommendFoodList: (value: foodType[]) => void;
}
const userStore = create(
  persist<userStoreType>(
    (set) => ({
      accessToken: '',
      setAccessToken: (value: string) => set({ accessToken: value }),
      refreshToken: '',
      setRefreshToken: (value: string) =>
        set({ refreshToken: value }),
      isLogin: false,
      setIsLogin: (value: boolean) => set({ isLogin: value }),
      isRecorded: false,
      setIsRecorded: (value: boolean) => set({ isRecorded: value }),
      answerList: [],
      updateAnswerList: (value: answerType[]) =>
        set({ answerList: value }),
      mbtiKey: '',
      setMbtiKey: (value: string) => set({ mbtiKey: value }),
      userMbti: {
        ei: 0,
        ns: 0,
        tf: 0,
        jp: 0,
        mint: 0,
        pine: 0,
        die: 0,
      },
      setUserMbti: (value: mbtiType) => set({ userMbti: value }),
      groupMbti: {
        ei: 0,
        ns: 0,
        tf: 0,
        jp: 0,
        mint: 0,
        pine: 0,
        die: 0,
      },
      setGroupMbti: (value: mbtiType) => set({ groupMbti: value }),
      groupId: 0,
      setGroupId: (value: number) => set({ groupId: value }),
      isSolo: true,
      setIsSolo: (value: boolean) => set({ isSolo: value }),
      isCreateModalOpen: false,
      setIsCreateModalOpen: (value: boolean) =>
        set({ isCreateModalOpen: value }),
      todayMemberList: [],
      setTodayMemberList: (value: memberType[]) =>
        set({ todayMemberList: value }),
      nextMemberList: [],
      setNextMemberList: (value: memberType[]) => set({ nextMemberList: value }),
      myMukboId: 0,
      setMyMukboId: (value: number) => set({ myMukboId: value }),
      recommendFoodList: [],
      setRecommendFoodList: (value: foodType[]) =>
        set({ recommendFoodList: value }),
      //   loginModalOpen: false,
      //   setLoginModalOpen: (value) => set({ loginModalOpen: value }),
      //   isMyPage: true,
      //   setIsMyPage: (value) => set({ isMyPage: value }),
      //   currentPageID: undefined,
      //   setCurrentPageID: (value) => set({ currentPageID: value }),
      //   loginAccount: {},
      //   setLoginAccount: (value) => set({ loginAccount: value }),
      //   followingUsers: [],
      //   setFollowingUsers: (value) => set({ followingUsers: value }),
    }),
    { name: 'userData' }
  )
);

export default userStore;
