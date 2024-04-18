import styles from '../../styles/mainPage/MainPage.module.css';

interface propsType {
  selectedFoodImg: string;
  selectedFoodName: string;
}

function SelectedFoodModal({
  selectedFoodImg,
  selectedFoodName,
}: propsType) {
  return (
    <div className={styles.selectedFoodModalWrapper}>
        <div className={styles.selectedFoodModalBox}>
          <h2>오늘의 메뉴</h2>
          <img src={selectedFoodImg} alt="" />
          <span>{selectedFoodName}</span>
        </div>
    </div>
  );
}

export default SelectedFoodModal;
