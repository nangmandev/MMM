import styles from '../../styles/groupPage/GroupPage.module.css';
import rewardCntIcon from '../../assets/images/rewardCntIcon.png';
import buttonStyles from '../../styles/common/Buttons.module.css';
import { useMutation, useQuery } from '@tanstack/react-query';
import { getReward, postTitleReward } from '../../api/rewardApi';
import userStore from '../../stores/userStore';
import RewardCard from './RewardCard';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

interface rewardType {
  id: number;
  condition: string;
  name: string;
  isCleared: boolean;
  imageSrc: string | undefined;
}

interface propsType {
  closeRewardModal: () => void;
}
function RewardModal({closeRewardModal}: propsType) {
  const { groupId } = userStore();
  const navigate = useNavigate();
  const { mutate: mutatePostTitleReward } = useMutation({
    mutationFn: postTitleReward,
    onSuccess: () => {
      navigate('/group');
      closeRewardModal()
    },
    onError: (error) => {
      console.error('에러났습니다.', error)
    }
  });

  const handlePostTitleReward = () => {
    mutatePostTitleReward({ groupId, selectedId });
  };

  const handleSetSelectedId = (id: number) => {
    setSelectedId(id);
    console.log(selectedId);
  };
  

  const {
    data: reward,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['reward'],
    queryFn: () => getReward(groupId),
  });
  const [selectedId, setSelectedId] = useState<number | null>(reward?.titleMukjukId);

  console.log('reward:', reward);

  if (isPending) {
    return <div>Loading...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }

  const rewardCnt = reward.badges.length;
  const myRewardList = reward.badges.filter((reward: rewardType) => {
    return reward.isCleared;
  });
  const myRewardCnt = myRewardList.length;
  const notMyRewardList = reward.badges.filter(
    (reward: rewardType) => {
      return !reward.isCleared;
    }
  );

  return (
    <div className={styles.rewardModalWrapper}>
      <h2>먹적</h2>
      <div>
        <h3 className={styles.myRewardTitle}>보유 먹적</h3>
        <div className={styles.rewardNumBox}>
          <img src={rewardCntIcon} alt="" />
          <span>
            {myRewardCnt}/{rewardCnt}
          </span>
        </div>
        <section className={styles.rewardSection}>
          {myRewardCnt ? (
            myRewardList.map((reward: rewardType) => {
              return (
                <RewardCard
                  id={reward.id}
                  condition={reward.condition}
                  name={reward.name}
                  isCleared={reward.isCleared}
                  imageSrc={reward.imageSrc}
                  handleSetSelectedId={handleSetSelectedId}
                  selectedId={selectedId}
                />
              );
            })
          ) : (
            <div className={styles.noRewardText}>
              보유한 먹적이 없습니다.
              <br />
              먹적을 달성해보세요!
            </div>
          )}
        </section>
      </div>
      <div>
        <h3 className={styles.notMyRewardTitle}>미보유 먹적</h3>
        <section className={styles.rewardSection}>
          {notMyRewardList.map((reward: rewardType) => {
            return (
              <RewardCard
                key={reward.id}
                id={reward.id}
                condition={reward.condition}
                name={reward.name}
                isCleared={reward.isCleared}
                imageSrc={reward.imageSrc}
                handleSetSelectedId={handleSetSelectedId}
                selectedId={selectedId}
              />
            );
          })}
        </section>
      </div>
      <button
        className={buttonStyles.miniRoundedButton}
        onClick={handlePostTitleReward}
      >
        확인
      </button>
    </div>
  );
}

export default RewardModal;
