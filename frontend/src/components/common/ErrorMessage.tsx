import styles from '../../styles/common/ErrorMessage.module.css';

interface ErrorMessageProps {
  errorMessage: string;
}

function ErrorMessage({ errorMessage }: ErrorMessageProps) {
  return (
    <div className={styles.errorMessage}>
      {errorMessage}
    </div>
  );
}

export default ErrorMessage;
