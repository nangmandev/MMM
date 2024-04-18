import styles from '../../styles/groupPage/MemberSection.module.css';
import editMukbot from '../../assets/images/editMukbot.png';
import MiniRedButton from './MiniRedButton';

interface SubMemberCardProps {
  articleName: string;
  memberName: string;
  memberMBTI: string;
  buttonName: string;
  clickEvent: () => void;
}

function SubMemberCard({
  articleName,
  memberName,
  memberMBTI,
  buttonName,
  clickEvent,
}: SubMemberCardProps) {
  return (
    <div className={styles.memberBox}>
      <div className={styles.memberCard}>
        {articleName == '먹봇' ? (
          <img src={editMukbot} alt="editMukbot" />
        ) : (
          <div></div>
        )}
        <div className={styles.memberName}>{memberName}</div>
        <div className={styles.memberMBTI}>{memberMBTI}</div>
      </div>
        <MiniRedButton clickEvent={clickEvent} buttonName={buttonName} />
    </div>
  );
}

export default SubMemberCard;
