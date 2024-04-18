import { useMutation, useQuery } from '@tanstack/react-query';
import { getUserMbti } from '../../api/mbtiApi';
import styles from '../../styles/mainPage/MainPage.module.css';
import buttonStyles from '../../styles/common/Buttons.module.css';
import userStore from '../../stores/userStore';
import { useEffect, useState } from 'react';
import MemberCard from './MemberCard';
import {
  getGroupMukbotList,
  getGroupUserList,
} from '../../api/memberApi';
import together from '../../assets/images/together.png';
import memberChange from '../../assets/images/memberChange.png';
import Modal from '../common/Modal';
import TodayMemberModal from './TodayMemberModal';
import { postTodayMemberMbti } from '../../api/groupApi';

interface propsType {
  hadleOpenCreateModal: () => void;
  isSolo: boolean;
  groupId: number;
  groupName: string;
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

function GroupSection({
  hadleOpenCreateModal,
  isSolo,
  groupId,
  groupName,
}: propsType) {
  const {
    setUserMbti,
    todayMemberList,
    nextMemberList,
    setTodayMemberList,
    setGroupMbti,
    groupMbti,
  } = userStore();
  const [isTodayMemberModalOpen, setIsTodayMemberModalOpen] =
    useState(false);
  const {
    data: userMbti,
    // isPending: isUserMbtiPending,
    isError,
  } = useQuery({
    queryKey: ['userMbti'],
    queryFn: getUserMbti,
    enabled: isSolo,
  });

  useEffect(() => {
    if (userMbti) {
      setUserMbti(userMbti);
      // console.log('userMbti:', userMbti)
    }
  }, [userMbti]);

  const openTodayMemberModal = () => {
    setIsTodayMemberModalOpen(true);
  };

  const closeTodayMemberModal = () => {
    setIsTodayMemberModalOpen(false);
  };

  const {
    data: groupUserList,
    isPending: isUserPending,
    isError: isUserListError,
  } = useQuery({
    queryKey: ['groupUserList'],
    queryFn: () => getGroupUserList(groupId),
    // enabled: !isSolo,
  });

  const { data: groupMukbotList } = useQuery({
    queryKey: ['groupMukbotList'],
    queryFn: () => getGroupMukbotList(groupId),
    enabled: !isSolo,
  });

  const { mutate: mutateTodayMemberMbti } = useMutation({
    mutationFn: postTodayMemberMbti,
    onSuccess: (data) => {
      setGroupMbti(data.mbti);
    },
  });

  // useEffect(() => {
  //   if (todayGroupMbti) {
  //     setGroupMbti(todayGroupMbti.mbti);
  //   }
  // }, [todayGroupMbti]);

  useEffect(() => {
    if (groupUserList && !groupMukbotList) {
      setTodayMemberList([...groupUserList.users]);
    } else if (groupUserList && groupMukbotList) {
      console.log(todayMemberList, groupUserList, groupMukbotList);
      const combinedMembers = [
        ...groupUserList.users,
        ...groupMukbotList.users,
      ];
      const newTodayMembers = combinedMembers.filter(
        (member) =>
          !nextMemberList.some(
            (todayMember) => todayMember.mukboId === member.mukboId
          )
      );
      setTodayMemberList(newTodayMembers);
      // setTodayMemberList([
      //   ...groupUserList?.users,
      //   ...groupMukbotList?.users,
      // ]);
    }
  }, [groupUserList, groupMukbotList]);

  useEffect(() => {
    if (todayMemberList) {
      mutateTodayMemberMbti({ groupId, todayMemberList });
    }
  }, [todayMemberList]);

  if (isUserPending) {
    return <div>Loading...</div>;
  }

  if (isUserListError || isError) {
    return <div>error</div>;
  }

  let mbtiString = '';
  mbtiString += groupMbti.ei > 15 ? 'I' : 'E';
  mbtiString += groupMbti.ns > 15 ? 'S' : 'N';
  mbtiString += groupMbti.tf > 15 ? 'F' : 'T';
  mbtiString += groupMbti.jp > 15 ? 'P' : 'J';

  return (
    <div className={styles.groupSection}>
      {isTodayMemberModalOpen && (
        <Modal clickEvent={closeTodayMemberModal}>
          <TodayMemberModal
            closeTodayMemberModal={closeTodayMemberModal}
          />
        </Modal>
      )}
      <div className={styles.header}>
        <h2>먹그룹</h2>
        {!isSolo && (
          <div onClick={openTodayMemberModal}>
            <span>오늘의 멤버 변경</span>
            <img src={memberChange} alt="" />
          </div>
        )}
      </div>
      <div className={styles.groupInfoBox}>
        {!isSolo ? (
          <div className={styles.groupName}>{groupName}</div>
        ) : (
          <div className={styles.soloGroupName}>그룹명</div>
        )}
        <div className={styles.soloGroupName}>
          {mbtiString || '먹BTI'}
        </div>
        {/* <div className={styles.soloGroupName}>{isSolo ? '먹BTI' : groupMbti}</div> */}
      </div>
      {isSolo ? (
        <main className={styles.soloMain}>
          <span>
            부서 내 팀원들을 초대하여 먹그룹을 만들어
            <br />
            대표 먹BTI를 설정해보세요.
          </span>
          <button
            className={buttonStyles.squaredButton}
            onClick={hadleOpenCreateModal}
          >
            먹그룹 만들기
          </button>
        </main>
      ) : (
        <main className={styles.groupMain}>
          <article>
            <h2 className={styles.todayTitle}>오늘 같이 먹어요</h2>
            <div>
              {todayMemberList.map((user: userType) => {
                return (
                  <MemberCard
                    key={user.mukboId}
                    mbti={user.mukBTI}
                    name={user.name}
                  />
                );
              })}
            </div>
          </article>
          <article>
            <h2 className={styles.todayNext}>아쉽지만 다음에...</h2>
            {nextMemberList.length ? (
              <div>
                {nextMemberList.map((user: userType) => {
                  return (
                    <MemberCard
                      key={user.mukboId}
                      mbti={user.mukBTI}
                      name={user.name}
                    />
                  );
                })}
              </div>
            ) : (
              <div className={styles.togetherBox}>
                <img src={together} alt="" />
                <span>오늘은 다같이 먹네요!</span>
              </div>
            )}
          </article>
        </main>
      )}
    </div>
  );
}

export default GroupSection;
