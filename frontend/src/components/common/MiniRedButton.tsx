import styles from '../../styles/common/Buttons.module.css';

interface ButtonProps {
  clickEvent: () => void;
  buttonName: string;
}

function MiniRedButton({ clickEvent, buttonName }: ButtonProps) {
  return (
      <div>
        {buttonName == '나' ? (
          <button onClick={clickEvent} className={styles.miniRedButton} disabled>
            {buttonName}
          </button>
        ) : (
          <button onClick={clickEvent} className={styles.miniRedButton}>
            {buttonName}
          </button>
        )}
      </div>
  )
}

export default MiniRedButton;
