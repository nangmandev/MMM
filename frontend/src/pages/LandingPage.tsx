import { Link } from 'react-router-dom';
import styles from '../styles/landingPage/LandingPage.module.css';
import buttonStyles from '../styles/common/Buttons.module.css';
import mainLogo from '../assets/images/mainLogo.png';
import { getRandomFoodList } from '../api/recommendApi';
import { useEffect, useState } from 'react';
import { useQuery } from '@tanstack/react-query';

function LandingPage() {
  const { data, isPending, isError } = useQuery({
    queryKey: ['randomFoodList'],
    queryFn: getRandomFoodList,
  });
  const [currentImageIndex, setCurrentImageIndex] =
    useState<number>(0);
  const [isHovered, setIsHovered] = useState<boolean>(false);

  useEffect(() => {
    const interval = setInterval(() => {
      if (!isHovered && data) {
        setCurrentImageIndex(
          (prevIndex) => (prevIndex + 1) % data.foods.length
        );
      }
    }, 500);

    return () => clearInterval(interval);
  }, [isHovered]);

  const handleMouseEnter = () => {
    setIsHovered(true);
  };

  const handleMouseLeave = () => {
    setIsHovered(false);
  };

  if (isPending) {
    return <div>isLoding...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }

  return (
    <div className={styles.wrapper}>
      <img className={styles.mainLogo} src={mainLogo} alt="" />
      <div
        className={styles.randomFoodBox}
        onMouseOver={handleMouseEnter}
        onMouseLeave={handleMouseLeave}
      >
        <img
          className={styles.randomFood}
          src={data.foods[currentImageIndex].imageSrc}
          alt=""
        />
        <span>{data.foods[currentImageIndex].name}</span>
      </div>

      <div>
        <Link to="/login" className={buttonStyles.landingButton}>
          로그인
        </Link>
        <Link to="/mbti/0" className={buttonStyles.landingButton}>
          먹BTI 검사
        </Link>
      </div>
    </div>
  );
}

export default LandingPage;
