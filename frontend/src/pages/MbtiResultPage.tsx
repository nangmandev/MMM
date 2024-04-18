// import MbtiSection from '../components/common/MbtiSection';
import styles from '../styles/mbtiResultPage/MbtiResultPage.module.css';
import buttonStyles from '../styles/common/Buttons.module.css';
import { useMutation } from 'react-query';
import { getMbtiResult, postMbtiResult } from '../api/mbtiApi';
import { useNavigate } from 'react-router-dom';
import userStore from '../stores/userStore';
import MbtiIntroduceSection from '../components/common/MbtiIntroduceSection';
import MbtiSection from '../components/common/MbtiSection';
import { useQuery } from '@tanstack/react-query';
import { useEffect } from 'react';
import subLogo from '../assets/images/subLogo.png';

function MbtiResultPage() {
  const { isLogin, answerList, mbtiKey, setMbtiKey } = userStore();
  console.log(answerList, isLogin);
  const navigate = useNavigate();

  const { mutate: mutateCreateUserMbti } = useMutation({
    mutationFn: postMbtiResult,
    onSuccess: () => navigate('/'),
  });

  const {
    data: mbtiResult,
    isPending,
    isError: getMbtiResultError,
  } = useQuery({
    queryKey: ['mbtiResult'],
    queryFn: () => getMbtiResult(answerList),
  });

  useEffect(() => {
    setMbtiKey(mbtiResult?.key);
  }, [mbtiResult]);

  const goNextStep = () => {
    if (isLogin) {
      console.log('mbtiKey:', mbtiKey);
      mutateCreateUserMbti(mbtiKey);
    } else {
      navigate('/signup');
    }
  };

  if (isPending) {
    return <div>isLoding...</div>;
  }
  if (getMbtiResultError) {
    return <div>error</div>;
  }

  return (
    <div className={styles.wrapper}>
      <img className={styles.subLogo} src={subLogo} alt="" />
      <div className={styles.mainSection}>
        <MbtiSection mbti={mbtiResult.mbti} />
        <MbtiIntroduceSection />
      </div>
      <button
        onClick={goNextStep}
        className={
          buttonStyles.miniRoundedButton + ' ' + styles.nextButton
        }
      >
        다음
      </button>
    </div>
  );
}

export default MbtiResultPage;
