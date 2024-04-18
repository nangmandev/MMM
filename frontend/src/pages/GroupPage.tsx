import GroupInfoSection from '../components/groupPage/GroupInfoSection';
import Header from '../components/groupPage/Header';
import MbtiSection from '../components/common/MbtiSection';
import MemberSection from '../components/groupPage/MemberSection';
import styles from '../styles/groupPage/GroupPage.module.css';
import { useQuery } from '@tanstack/react-query';
import { getGroupInfo } from '../api/groupApi';

function GroupPage() {
  const mbti = {
    ei: 1,
    ns: 2,
    tf: 3,
    jp: 4,
    mint: 4,
    pine: 4,
    die: 4,
  };
  const {
    data: groupInfo,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['groupInfo'],
    queryFn: getGroupInfo,
  });

  console.log('groupInfo:', groupInfo)
  if (isPending) {
    return <div>isLoding...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }

  return (
    <div className={styles.wrapper}>
      <Header />
      <GroupInfoSection />
      <MbtiSection mbti={mbti} />
      <MemberSection groupId={groupInfo.mukgroupId} />
    </div>
  );
}

export default GroupPage;
