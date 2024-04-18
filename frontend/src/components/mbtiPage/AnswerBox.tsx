import styles from '../../styles/mbtiPage/MbtiPage.module.css';

interface propsType {
  handleSelectAnswer: () => void;
  isSelected: boolean;
  text: string;
  imgSrc: string;
}
function AnswerBox({
  handleSelectAnswer,
  isSelected,
  text,
  imgSrc,
}: propsType) {
  return (
    <div className={isSelected ? styles.selectedAnswerBox : styles.answerBox} onClick={handleSelectAnswer}>
      <span>{text}</span>

      <div>
        <img src={imgSrc} alt="" />
      </div>
    </div>
  );
}

export default AnswerBox;
