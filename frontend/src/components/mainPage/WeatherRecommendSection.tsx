import styles from '../../styles/mainPage/MainPage.module.css';
import { useQuery } from '@tanstack/react-query';
import { getWeatherRecommendFood } from '../../api/recommendApi';
import { useEffect, useState } from 'react';

function WeatherRecommendSection() {
  const [latitude, setLatitude] = useState<number | null>(null);
  const [longitude, setLongitude] = useState<number | null>(null);

  useEffect(() => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function (position) {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;

        setLatitude(latitude);
        setLongitude(longitude);
      });
    }
  }, []);

  const {
    data: weatherRecommendFood,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['weatherRecommendFood'],
    queryFn: () =>
      getWeatherRecommendFood(String(latitude), String(longitude)),
    enabled: latitude !== null && longitude !== null,
  });

  if (isPending) {
    return <div>Loading...</div>;
  }
  if (isError) {
    return null;
  }

  const content =
    weatherRecommendFood.weatherName === '무더위'
      ? '해가 쨍쨍한데'
      : '비도 오는데';
  return (
    <section className={styles.weatherRecommendSection}>
      <div className={styles.textBox}>
        <h2>날씨별 추천</h2>
        <div>
          {content}
          <br />
          혹시{' '}
          <span className={styles.foodName}>
            [ {weatherRecommendFood.foodInformation.name} ]
          </span>{' '}
          어때요?
        </div>
      </div>
      <img
        src={weatherRecommendFood.foodInformation.imageSrc}
        alt=""
      />
    </section>
  );
}

export default WeatherRecommendSection;
