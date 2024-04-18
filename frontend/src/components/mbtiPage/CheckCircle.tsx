import styles from '../../styles/mbtiPage/MbtiPage.module.css';

interface propsType {
  isSelected: boolean;
}
function CheckCircle({ isSelected }: propsType) {
  return (
    <div
      className={styles.parentCheckCircle}
    >
      <div className={styles.circleLine} />
      <div
        className={
          isSelected
            ? styles.selectedChildCheckCircle
            : styles.childCheckCircle
        }
      ></div>
    </div>
  );
}

export default CheckCircle;
