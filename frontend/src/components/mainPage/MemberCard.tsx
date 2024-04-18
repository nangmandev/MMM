import styles from '../../styles/mainPage/MainPage.module.css';

interface propsType {
  mbti: string;
  name: string;
}
function MemberCard({ mbti, name }: propsType) {
  return (
    <div className={styles.memberCard}>
      <span className={styles.nickname}>{name}</span>
      <span className={styles.mbti}>{mbti}</span>
    </div>
  );
}

export default MemberCard;
