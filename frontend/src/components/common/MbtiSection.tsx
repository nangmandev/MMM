// import { useQuery } from '@tanstack/react-query';
import styles from '../../styles/common/MbtiSection.module.css';
// import { getMbtiResult } from '../../api/mbtiApi';

interface firstSecondType {
  eng: string;
  kor: string;
}
interface mbtiOptionsType {
  id: number;
  first: firstSecondType;
  second: firstSecondType;
  barColor: string;
  name: string;
}
const MBTI_OPTIONS: mbtiOptionsType[] = [
  {
    id: 1,
    first: { eng: 'Emergency', kor: '맵찌질' },
    second: { eng: 'Ignite', kor: '맵당당' },
    barColor: '#FF0000',
    name: 'ei',
  },
  {
    id: 2,
    second: { eng: 'Ssal', kor: '밥파' },
    first: { eng: 'Noodle', kor: '면파' },
    barColor: '#FFB800',
    name: 'ns',
  },
  {
    id: 3,
    second: { eng: 'Fitness', kor: '건강식' },
    first: { eng: 'Total', kor: '일반식' },
    barColor: '#11CF00',
    name: 'tf',
  },
  {
    id: 4,
    first: { eng: 'Jammine', kor: '잼민입맛' },
    second: { eng: 'Papa', kor: '아재입맛' },
    barColor: '#0029FF',
    name: 'jp',
  },
];

interface mbtiType {
  ei: number;
  ns: number;
  tf: number;
  jp: number;
  mint: number;
  pine: number;
  die: number;
  [key: string]: number;
}

interface propsType {
  mbti: mbtiType;
}

function MbtiSection({ mbti }: propsType) {
  // const { data, isPending, isError } = useQuery({
  //   queryKey: ['mbtiResult'],
  //   queryFn: getMbtiResult
  // })

  // if (isPending) {
  //   return <div>isLoding...</div>;
  // }
  // if (isError) {
  //   return <div>error</div>;
  // }
  let mbtiString = '';
  mbtiString += mbti.ei > 15 ? 'I' : 'E';
  mbtiString += mbti.ns > 15 ? 'S' : 'N';
  mbtiString += mbti.tf > 15 ? 'F' : 'T';
  mbtiString += mbti.jp > 15 ? 'P' : 'J';

  return (
    <section className={styles.mbtiSection}>
      <div className={styles.mbtiTitleBox}>
        <span>먹BTI</span>
        <div>{mbtiString}</div>
      </div>
      {MBTI_OPTIONS.map((mbtiOption) => {
        const mbtiScore = Math.round(
          (mbti[mbtiOption.name] / 3) * 10
        );

        const absMbtiScore = 50 + Math.abs(50 - mbtiScore);

        return (
          <div className={styles.mbtiBarBox} key={mbtiOption.id}>
            <div className={styles.mbtiNameBox}>
              <span className={styles.mbtiNameEng}>
                {mbtiOption.first.eng}
              </span>
              <span className={styles.mbtiNameKor}>
                {mbtiOption.first.kor}
              </span>
            </div>
            <div className={styles.mbtiBarBackGround}>
              <span>{absMbtiScore}</span>
              <div
                className={
                  mbti[mbtiOption.name] > 15
                    ? styles.rightMbtiBar
                    : styles.leftMbtiBar
                }
                style={{
                  backgroundColor: mbtiOption.barColor,
                  width: `${absMbtiScore}%`,
                }}
              ></div>
            </div>
            <div className={styles.mbtiNameBox}>
              <span className={styles.mbtiNameEng}>
                {mbtiOption.second.eng}
              </span>
              <span className={styles.mbtiNameKor}>
                {mbtiOption.second.kor}
              </span>
            </div>
          </div>
        );
      })}
    </section>
  );
}

export default MbtiSection;
