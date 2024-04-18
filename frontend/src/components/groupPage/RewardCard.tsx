import styles from '../../styles/groupPage/GroupPage.module.css';
import lock from '../../assets/images/lock.png';
import secret from '../../assets/images/secret.png';

interface propsType {
  id: number;
  condition: string;
  name: string;
  isCleared: boolean;
  imageSrc: string | undefined;
  selectedId: number | null;
  handleSetSelectedId: (id: number) => void;
}

function RewardCard({
  id,
  condition,
  name,
  isCleared,
  imageSrc,
  selectedId,
  handleSetSelectedId,
}: propsType) {
  return (
    <article
      className={styles.rewardItem}
      onClick={() => handleSetSelectedId(id)}
    >
      <div
        className={
          isCleared
            ? selectedId === id
              ? styles.selectedRewardImgBox
              : styles.myRewardImgBox
            : styles.notMyRewardImgBox
        }
      >
        <img src={imageSrc} alt="" />
        {!isCleared && (
          <img
            className={styles.notMyRewardIcon}
            src={condition === '비밀~' ? secret : lock}
          />
        )}
      </div>
      <span className={styles.rewardName}>{name}</span>
      <p>{condition}</p>
    </article>
  );
}

export default RewardCard;
