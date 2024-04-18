import { useRef, useEffect } from 'react';
import styles from '../../styles/mainPage/MainPage.module.css';
import roulleteArrow from '../../assets/images/roulleteArrow.png';

interface propsType {
  mainRecommendFoodList: any;
  openSelectedFoodModal: () => void;
  setSelectedFoodId: any;
}

const CanvasRoulette = ({
  mainRecommendFoodList,
  openSelectedFoodModal,
  setSelectedFoodId,
}: propsType) => {
  const canvasRef = useRef<HTMLCanvasElement>(null);

  const product: string[] = [];

  mainRecommendFoodList.map((food: any) => {
    product.push(food.name);
  });
  const colors: string[] = [
    '#FFCCCC',
    '#FFD7A8',
    '#FFECB2',
    '#99DFB9',
    '#B4C7E7',
  ];

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    const [cw, ch] = [canvas.width / 2, canvas.height / 2];
    const arc = Math.PI / (product.length / 2);

    for (let i = 0; i < product.length; i++) {
      ctx.beginPath();
      ctx.fillStyle = colors[i];
      ctx.moveTo(cw, ch);
      ctx.arc(cw, ch, cw, arc * (i - 1), arc * i);
      ctx.fill();
      ctx.closePath();
    }

    ctx.fillStyle = 'black';
    ctx.font = '14px Pretendard';
    ctx.textAlign = 'center';

    for (let i = 0; i < product.length; i++) {
      const angle = arc * i + arc / 2;

      ctx.save();

      ctx.translate(
        cw + Math.cos(angle) * (cw - 50),
        ch + Math.sin(angle) * (ch - 50)
      );

      ctx.rotate(angle + Math.PI / 2);

      product[i].split(' ').forEach((text, j) => {
        ctx.fillText(text, 0, 30 * j);
      });

      ctx.restore();
    }
  }, []);

  const rotate = () => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    canvas.style.transform = `initial`;
    canvas.style.transition = `initial`;

    setTimeout(() => {
      const ran = Math.floor(Math.random() * product.length);
      const arc = 360 / product.length;
      const rotate = ran * arc + 3600 + arc * 3 - arc / 4;

      canvas.style.transform = `rotate(-${rotate}deg)`;
      canvas.style.transition = `2s`;
      const food = mainRecommendFoodList.find((item: any) => {
        console.log(ran, item);
        return item.name === product[ran];
      });
      console.log('gdgdgd:', food);
      setSelectedFoodId(ran+1);
      setTimeout(
        () => openSelectedFoodModal(),
        // () => alert(`오늘의 야식은?! ${product[ran]} 어떠신가요?`),
        2000
      );
    }, 1);
  };

  return (
    <div className={styles.roulleteWrapper}>
      <img src={roulleteArrow} alt="" />
      <canvas ref={canvasRef} width="210" height="210"></canvas>
      <button className={styles.stopButton} onClick={rotate}>
        돌리기
      </button>
    </div>
  );
};

export default CanvasRoulette;
