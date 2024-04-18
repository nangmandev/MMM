import { useQuery } from '@tanstack/react-query';
import MbtiIntroduceSection from '../components/common/MbtiIntroduceSection';
import MbtiSection from '../components/common/MbtiSection';
import styles from '../styles/introducePage/IntroducePage.module.css';
import { getUserMbti } from '../api/mbtiApi';

function IntroducePage() {
  const {
    data: mbti,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['userMbti'],
    queryFn: getUserMbti,
  });

  if (isPending) {
    return <div>isLoding...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }
  return (
    <div className={styles.wrapper}>
      <MbtiSection mbti={mbti} />
      <MbtiIntroduceSection />
    </div>
  );
}

export default IntroducePage;
