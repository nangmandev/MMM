import ProfileImgBox from '../common/ProfileImgBox';
import styles from '../../styles/groupPage/GroupPage.module.css';
import { useEffect, useState } from 'react';
import SmallLabelInput from '../common/SmallLabelInput';
import Button from '../common/Button';
import { getGroupInfo, putGroupName } from '../../api/groupApi';
import { useMutation, useQuery } from '@tanstack/react-query';
import userStore from '../../stores/userStore';
import Modal from '../common/Modal';
import RewardModal from './RewardModal';
// import { useQuery } from '@tanstack/react-query';
// import { getGroupInfo } from '../../api/groupApi';

function GroupInfoSection() {
  const { groupId } = userStore();
  const [isRewardModalOpen, setIsRewardModalOpen] = useState(false);

  const {
    data: groupInfo,
    isPending,
    isError,
  } = useQuery({
    queryKey: ['groupInfo'],
    queryFn: getGroupInfo,
    enabled: !isRewardModalOpen,
  });

  const { mutate: mutatePutGroupName } = useMutation({
    mutationFn: putGroupName,
  });
  console.log('groupInfo:', groupInfo);
  const [groupName, setGroupName] = useState<string>('');
  const [groupImg, setGroupImg] = useState<string>('');
  const [isGroupNameValid] = useState<boolean>(true);
  const [isGroupNameDuplicated] = useState<boolean>();

  console.log(groupImg)
  
  const handleOpenRewardModal = () => {
    setIsRewardModalOpen(true);
  };
  const closeRewardModal = () => {
    setIsRewardModalOpen(false);
  };

  useEffect(() => {
    setGroupName(groupInfo.name);
    if (groupInfo.imageSrc !== null) {
      setGroupImg(groupInfo.imageSrc)
    }
  }, [groupInfo.groupId]);

  const handlePutGroupName = () => {
    mutatePutGroupName({ groupId, groupName });
  };

  const changeGroupName = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    setGroupName(e.target.value);
  };

  const checkGroupName = () => {};

  if (isPending) {
    return <div>isLoding...</div>;
  }
  if (isError) {
    return <div>error</div>;
  }

  return (
    <section className={styles.groupInfoSection}>
      <div className={styles.profileBox}>
        <span>대표사진</span>
        <ProfileImgBox imageSrc={groupImg} setImageSrc={setGroupImg} mode={"MODIFY"}/>
      </div>
      <div className={styles.groupNameBox}>
        <SmallLabelInput
          title="그룹명"
          info={'2~20자(영어(대/소), 숫자)'}
          inputName="password"
          inputValue={groupName}
          onChange={changeGroupName}
          inputType="text"
          inputWidth="shortInput"
          onBlur={checkGroupName}
          isInputValid={isGroupNameValid}
          errorMessage="그룹명 형식이 잘못되었습니다."
        />
        <Button
          clickEvent={handlePutGroupName}
          buttonName={isGroupNameDuplicated == false ? '✓' : '확인'}
          disabledEvent={isGroupNameDuplicated == false}
        />
      </div>
      <div className={styles.rewardBox}>
        <span>먹적</span>
        <div onClick={handleOpenRewardModal}>
          {groupInfo.titleMukjukImage && (
            <img src={groupInfo.titleMukjukImage} alt="" />
          )}
          <span>
            {groupInfo.titleMukjukName || '대표먹적이 없습니다.'}
          </span>
        </div>
      </div>
      {isRewardModalOpen && (
        <Modal clickEvent={closeRewardModal}>
          <RewardModal closeRewardModal={closeRewardModal} />
        </Modal>
      )}
    </section>
  );
}

export default GroupInfoSection;
