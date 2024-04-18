// import { useQuery } from '@tanstack/react-query';
import styles from '../../styles/mainPage/MainPage.module.css';
import buttonStyles from '../../styles/common/Buttons.module.css';
import { getRecentRecommendFood, postRecord } from '../../api/recommendApi';
import { useMutation, useQuery } from '@tanstack/react-query';
import { useState } from 'react';
// import { getRecentRecommendFood } from '../../api/recommendApi';

interface foodType {
  recommendedFoodId: number;
  name: string;
  img: string;
}
interface propsType {
  handleCloseModal: () => void;
  groupId: number;
  // date: string;
  // recentFoodList: foodType[];
  // recentFoodListPending: boolean;
}
function RecordModal({
  handleCloseModal,
  groupId,
  // date,
  // recentFoodList,
  // recentFoodListPending,
}: propsType) {
  const [isSelectedId, setIsSelectedId] = useState<number | null>(
    null
  );
  const {
    data: recentFoodList,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['recentRecommendFood'],
    queryFn: () => getRecentRecommendFood(groupId),
    enabled: groupId !== undefined,
  });

  const { mutate: mutatePostRecord } = useMutation({
    mutationFn: postRecord,
    onSuccess: () => {
      handleCloseModal();
    }
  })

  const handleSelectFood = (recommendedFoodId: number) => {
    setIsSelectedId(recommendedFoodId);
  };

  const handleCreateRecord = (isSelectedId: number) => {
    mutatePostRecord({isSelectedId, groupId})
  }

  if (isPending) {
    return <div>Loading...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }

  console.log(recentFoodList);
  return (
    <div className={styles.recordModalWrapper}>
      <h2>먹기록</h2>
      <p>
        {recentFoodList?.data.date}에 추천받은 메뉴 중<br />
        <span className={styles.boldWord}>어떤 메뉴</span>를 드셨나요?
      </p>
      <section className={styles.recommendBox}>
        {recentFoodList?.data.foods.map((food: foodType) => {
          return (
            <div
              className={styles.recommendItem}
              onClick={() => handleSelectFood(food.recommendedFoodId)}
            >
              <div
                className={
                  isSelectedId === food.recommendedFoodId
                    ? styles.selectedImgBox
                    : styles.imgBox
                }
              >
                <img src={food.img} alt="" />
              </div>
              <span
                className={
                  isSelectedId === food.recommendedFoodId
                    ? styles.selectedText
                    : styles.text
                }
              >
                {food.name}
              </span>
            </div>
          );
        })}
      </section>
      <div className={styles.buttonBox}>
        <button
          onClick={() => handleCreateRecord(isSelectedId)}
          className={buttonStyles.miniRoundedButton}
        >
          확인
        </button>
        <button
          onClick={handleCloseModal}
          className={buttonStyles.miniRoundedButton}
        >
          없어요
        </button>
      </div>
    </div>
  );
}

export default RecordModal;
