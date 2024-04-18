import { useQuery } from '@tanstack/react-query';
import userStore from '../../stores/userStore';
import styles from '../../styles/groupPage/GroupPage.module.css';
import { getLog } from '../../api/groupApi';
import { formatDate } from '../../utils/TimestampParser.ts';

interface contentType {
  content: string;
  createdAt: number;
}
function LogBox() {
  const { groupId } = userStore();
  const {
    data: log,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['log'],
    queryFn: () => getLog(groupId),
  });
  console.log('mukLog:', log);

  if (isPending) {
    return <div className={styles.logBoxWrapper}>isLoding...</div>;
  }
  if (isError) {
    return <div className={styles.logBoxWrapper}>error</div>;
  }

  return (
    <div className={styles.logBoxWrapper}>
      {log.contents.length === 0 ? (
        <p>먹로그가 없습니다.</p>
      ) : (
        log.contents.map((content: contentType) => {
          return (
            <article className={styles.log}>
              <p>{content.content}</p>
              <span>{formatDate(content.createdAt)}</span>
            </article>
          );
        })
      )}
    </div>
  );
}

export default LogBox;
