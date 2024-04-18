import { useState } from 'react';
import styles from '../../styles/mainPage/MainPage.module.css';
import buttonStyles from '../../styles/common/Buttons.module.css';
import Input from '../common/Input';
import ProfileImgBox from '../common/ProfileImgBox';
import { postGroupInfo } from '../../api/groupApi';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { useNavigate } from 'react-router-dom';
import userStore from '../../stores/userStore';

function CreateGroupModal() {
  const { setIsCreateModalOpen, setIsSolo } = userStore();
  const queryClient = useQueryClient();
  const navigate = useNavigate();
  const [groupName, setGroupName] = useState('');
  const [groupImg, setGroupImg] = useState<string>('');
  const [isGroupNameValid] = useState(true);
  const { mutate: mutateCreateGroup } = useMutation({
    mutationFn: postGroupInfo,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['groupInfo'] });
      setIsCreateModalOpen(false);
      setIsSolo(false);
      navigate('/');
    },
    onError: (error) => {
      console.error('에러발생:', error);
    },
  });
  const handleChangeGroupName = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    setGroupName(e.target.value);
  };

  const handleCreateGroup = () => {
    mutateCreateGroup(groupName);
  };

  const checkGroupName = () => {};

  return (
    <div className={styles.createGroupModalWrapper}>
      <h2>먹그룹 생성</h2>
      <div className={styles.imgBox}>
        <span>대표사진</span>
        <ProfileImgBox
          imageSrc={groupImg}
          setImageSrc={setGroupImg}
          mode={'CREATE'}
        />
      </div>
      <div>
        <Input
          title="그룹명"
          info="2~20자 (한글, 영어(대/소), 숫자)"
          inputName="groupName"
          inputValue={groupName}
          onChange={handleChangeGroupName}
          inputType="text"
          inputWidth="longInput"
          onBlur={checkGroupName}
          isInputValid={isGroupNameValid}
          errorMessage="그룹명 형식이 잘못되었습니다."
        />
      </div>
      <button
        className={buttonStyles.miniBlueRoundedButton}
        onClick={handleCreateGroup}
      >
        완료
      </button>
    </div>
  );
}

export default CreateGroupModal;
