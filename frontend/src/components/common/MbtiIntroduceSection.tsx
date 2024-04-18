import styles from '../../styles/common/MbtiIntroduceSection.module.css';
import ignite from '../../assets/images/ignite.png';
import emergency from '../../assets/images/emergency.png';
import ssal from '../../assets/images/ssal.png';
import noodle from '../../assets/images/noodle.png';
import fitness from '../../assets/images/fitness.png';
import total from '../../assets/images/total.png';
import papa from '../../assets/images/papa.png';
import jammin from '../../assets/images/jammin.png';

interface firstSecondType {
  eng: string;
  kor: string;
  img: string;
}

interface mbtiIntroduceOptionsType {
  id: number;
  title: string;
  first: firstSecondType;
  second: firstSecondType;
}

const MBTI_INTRODUCE_OPTIONS: mbtiIntroduceOptionsType[] = [
  {
    id: 1,
    title: '매운맛 척도',
    first: {
      eng: 'Ignite',
      kor: '매운 맛이 두렵지 않은\n맵당당이',
      img: ignite,
    },
    second: {
      eng: 'Emergency',
      kor: '눈물 쏙! 콧물 쏙!\n맵찔이',
      img: emergency,
    },
  },
  {
    id: 2,
    title: '선호 탄수화물',
    first: {
      eng: 'Ssal',
      kor: '한국인은 밥심!\n밥파',
      img: ssal,
    },
    second: {
      eng: 'Noodle',
      kor: '면치기의 나라!\n면파',
      img: noodle,
    },
  },
  {
    id: 3,
    title: '건강 중요도',
    first: {
      eng: 'Fitness',
      kor: '맛보다는 건강!\n건강식파',
      img: fitness,
    },
    second: {
      eng: 'Total',
      kor: '먹을 때 제일 행복해!\n일반식파',
      img: total,
    },
  },
  {
    id: 4,
    title: '입맛의 성숙함',
    first: {
      eng: 'Jammin',
      kor: '엄망 소세지 없엉?\n잼민입맛',
      img: jammin,
    },
    second: {
      eng: 'Papa',
      kor: '점심은 뜨끈한 국밥!\n아재입맛',
      img: papa,
    },
  },
];

function MbtiIntroduceSection() {
  return (
    <div className={styles.wrapper}>
      <section className={styles.mbtiIntroduceSection}>
        <h2>먹BTI란?</h2>
        <p>
          사람의 <strong>입맛</strong>을 <strong>16가지 유형</strong>
          으로 나누는 것으로,
          <br />
          입맛 유형을 구별하여 각자 적합한 입맛을
          <br />
          찾을 목적으로 2024년에 개발되었다.
          <br />
          먹BTI에서는 다음과 같이 4가지 선호 경향으로 분류한다.
        </p>
      </section>
      <section className={styles.mbtiIntroduceOptionSection}>
        {MBTI_INTRODUCE_OPTIONS.map((mbtiIntroduceOption) => {
          return (
            <article key={mbtiIntroduceOption.id}>
              <h2>{mbtiIntroduceOption.title}</h2>
              <div className={styles.mbtiIntroduceOptionBox}>
                <div className={styles.mbtiIntroduceOptionItem}>
                  <img src={mbtiIntroduceOption.first.img} alt="" />
                  <span>{mbtiIntroduceOption.first.eng}</span>
                  <p>{mbtiIntroduceOption.first.kor}</p>
                </div>
                <div className={styles.vsText}>VS</div>
                <div className={styles.mbtiIntroduceOptionItem}>
                  <img src={mbtiIntroduceOption.second.img} alt="" />
                  <span>{mbtiIntroduceOption.second.eng}</span>
                  <p>{mbtiIntroduceOption.second.kor}</p>
                </div>
              </div>
            </article>
          );
        })}
      </section>
    </div>
  );
}

export default MbtiIntroduceSection;
