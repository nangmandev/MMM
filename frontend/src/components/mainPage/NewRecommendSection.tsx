import styles from '../../styles/mainPage/MainPage.module.css';
import { useQuery } from '@tanstack/react-query';
import { getNewRecommendFood } from '../../api/recommendApi';

interface propsType {
  groupId: number;
}

function NewRecommendSection({ groupId }: propsType) {
  const { data: newRecommendFood, isPending, isError } =useQuery({
    queryKey: ['newRecommendFood'],
    queryFn: () => getNewRecommendFood(groupId)
  })

  if (isPending) {
    return <div>isLoding...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }

  return (
    <section className={styles.newRecommendSection}>
      <div className={styles.textBox}>
        <h2>신메뉴 추천</h2>
        <div>
          오늘은 새로운 메뉴를 시도해보세요!
          <br />
          혹시 <span className={styles.foodName}>[ {newRecommendFood.name} ]</span> 어때요?
        </div>
      </div>
      <img src={newRecommendFood.image} alt='' />
    </section>
  );
}

export default NewRecommendSection;
