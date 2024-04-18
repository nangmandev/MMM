import styles from '../../styles/common/Input.module.css';
import ErrorMessage from './ErrorMessage';

interface InputProps {
  title: string;
  info: string;
  inputName: string;
  inputType: string;
  inputWidth: string;
  inputValue: string;
  // setInputValue: React.Dispatch<React.SetStateAction<string>>;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onBlur: (e: React.FocusEvent<HTMLInputElement>) => void;
  isInputValid: boolean;
  errorMessage: string;
}

function Input({
  title,
  info,
  inputName,
  inputType,
  inputWidth,
  inputValue,
  onChange,
  onBlur,
  isInputValid,
  errorMessage,
}: InputProps) {
  // const changeInputValue = (
  //   e: React.ChangeEvent<HTMLInputElement>
  // ) => {
  //   console.log(e.target.value);
  //   setInputValue(e.target.value);
  // };

  return (
    <div className={styles.inputContainer}>
      <label>{title}</label>
      <div className={styles.inputBox}>
        <input
          name={inputName}
          type={inputType}
          className={`${styles[inputWidth]}`}
          value={inputValue}
          onChange={onChange}
          onBlur={onBlur}
        />
        <span>{info}</span>
        {!isInputValid && (
          <ErrorMessage errorMessage={errorMessage} />
        )}
      </div>
    </div>
  );
}

export default Input;
