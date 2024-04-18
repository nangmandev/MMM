import styles from '../../styles/groupPage/MemberSection.module.css';

interface SubMemberArticleProps {
  children: React.ReactNode;
  articleName: string;
  modalButton: string;
  clickEvent: () => void;
}

function SubMemberArticle({
  articleName,
  children,
  modalButton,
  clickEvent
}: SubMemberArticleProps) {


  return (
    <article>
      <span className={styles.articleName}>{articleName}</span>
      <span className={styles.modalButton} onClick={clickEvent}>{modalButton}</span>
      {children}
    </article>
  );
}

export default SubMemberArticle;
