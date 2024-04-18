import styles from '../../styles/common/Buttons.module.css';

interface ButtonProps {
  clickEvent: () => void;
  disabledEvent: boolean;
  buttonName: string;
}

function Button ({ clickEvent, buttonName, disabledEvent }: ButtonProps) {
  return (
    <button onClick={clickEvent} disabled={disabledEvent} className={styles.miniBlueButton}>
      {buttonName}
    </button>
  );
}

export default Button;
