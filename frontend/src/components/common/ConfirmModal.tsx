import styles from '../../styles/common/ConfirmModal.module.css';
import buttonStyles from '../../styles/common/Buttons.module.css';

interface propsType {
  content: string;
  yesEvent: () => void;
  noEvent: () => void;
}
function ConfirmModal({ content, yesEvent, noEvent }: propsType) {
  return (
    <div className={styles.wrapper}>
      <p>{content}</p>
      <div>
        <button
          className={buttonStyles.miniRoundedButton}
          onClick={yesEvent}
        >
          예
        </button>
        <button
          className={buttonStyles.miniRoundedButton}
          onClick={noEvent}
        >
          아니오
        </button>
      </div>
    </div>
  );
}

export default ConfirmModal;
