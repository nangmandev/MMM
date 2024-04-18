import styles from '../../styles/common/Modal.module.css';
import closeMark from '../../assets/images/closeMark.png';

interface propsType {
  clickEvent: () => void;
  children: JSX.Element;
}
function Modal({ clickEvent, children }: propsType) {
  return (
    <>
      <div className={styles.modal}>
        <div className={styles.closeButton}>
          <img onClick={clickEvent} src={closeMark} />
        </div>
        <div className={styles.modalContent}>{children}</div>
      </div>
      <div className={styles.overlay}></div>
    </>
  );
}

export default Modal;
