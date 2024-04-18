import styles from '../../styles/mainPage/MainPage.module.css';
import memberCnt from '../../assets/images/memberCnt.png';
import userStore from '../../stores/userStore';
import buttonStyles from '../../styles/common/Buttons.module.css';
import { useMutation } from '@tanstack/react-query';
import { postTodayMember } from '../../api/recommendApi';

interface propsType {
  closeTodayMemberModal: () => void;
}
function TodayMemberModal({ closeTodayMemberModal }: propsType) {
  const {
    groupId,
    todayMemberList,
    nextMemberList,
    setTodayMemberList,
    setNextMemberList,
    setGroupMbti,
    setRecommendFoodList,
  } = userStore();

  const handleGoNextMember = (memberId: number) => {
    const updatedtodayMemberList = todayMemberList.filter(
      (member) => member.mukboId !== memberId
    );
    setTodayMemberList(updatedtodayMemberList);
    const memberToMove = todayMemberList.find(
      (member) => member.mukboId === memberId
    );
    if (nextMemberList) {
      setNextMemberList([...nextMemberList, memberToMove]);
    } else {
      setNextMemberList([memberToMove]);
    }
  };

  const handleGoTodayMember = (memberId: number) => {
    const updatedtodayMemberList = nextMemberList.filter(
      (member) => member.mukboId !== memberId
    );
    setNextMemberList(updatedtodayMemberList);
    const memberToMove = nextMemberList.find(
      (member) => member.mukboId === memberId
    );
    setTodayMemberList([...todayMemberList, memberToMove]);
  };

  const { mutate: mutateTodayMember } = useMutation({
    mutationFn: postTodayMember,
    onSuccess: (data) => {
      closeTodayMemberModal();
      setGroupMbti(data.mbti);
      setRecommendFoodList(data.lunchList);
    },
    onError: (error) => {
      console.error('에러발생:', error);
    },
  });
  const handleChangeTodayMemeber = () => {
    mutateTodayMember({ groupId, todayMemberList });
  };

  return (
    <div className={styles.todayMemberModalWrapper}>
      <div className={styles.todayMemberHeader}>
        <h2>오늘의 멤버 변경</h2>
        <div>
          <img src={memberCnt} alt="" />
          <span>
            {todayMemberList.length}/
            {todayMemberList.length + nextMemberList.length}
          </span>
        </div>
      </div>
      <section className={styles.todayMemberSection}>
        <h5>오늘 같이 먹어요</h5>
        <div className={styles.todayMemberBox}>
          {todayMemberList.map((member) => {
            return (
              <article
                key={member.mukboId}
                className={styles.memberBox}
              >
                <div className={styles.memberWhiteBox}>
                  <span className={styles.memberName}>
                    {member.name}
                  </span>
                  <span className={styles.memberMBTI}>
                    {member.mukBTI}
                  </span>
                </div>
                <button
                  onClick={() => handleGoNextMember(member.mukboId)}
                  className={styles.changeButton}
                >
                  -
                </button>
              </article>
            );
          })}
        </div>
      </section>
      <section className={styles.nextMemberSection}>
        <h5>아쉽지만 다음에...</h5>
        <div className={styles.todayMemberBox}>
          {nextMemberList?.map((member) => {
            return (
              <article
                key={member.mukboId}
                className={styles.memberBox}
              >
                <div className={styles.memberWhiteBox}>
                  <span className={styles.memberName}>
                    {member.name}
                  </span>
                  <span className={styles.memberMBTI}>
                    {member.mukBTI}
                  </span>
                </div>
                <button
                  className={styles.changeButton}
                  onClick={() => handleGoTodayMember(member.mukboId)}
                >
                  +
                </button>
              </article>
            );
          })}
        </div>
      </section>
      <button
        className={buttonStyles.miniBlueRoundedButton}
        onClick={handleChangeTodayMemeber}
      >
        완료
      </button>
    </div>
  );
}

export default TodayMemberModal;
