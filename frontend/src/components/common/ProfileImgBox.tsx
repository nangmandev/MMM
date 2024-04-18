import React, { Dispatch, SetStateAction, useRef } from 'react';
import member from '../../assets/images/member.png';
import styles from '../../styles/common/ProfileImgBox.module.css';
import { modifyGroupImage } from '../../api/groupApi.ts';
import userStore from '../../stores/userStore.ts';

interface ProfileImgBoxProps{
  mode : string
  imageSrc : string
  setImageSrc:  Dispatch<SetStateAction<string>>
}
function ProfileImgBox({ mode, imageSrc, setImageSrc}:ProfileImgBoxProps) {

  const { groupId } = userStore();

  const ref =  useRef<HTMLInputElement>(null);
  const handleUploadImg = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    const files = e.target.files;
    const uploadedImages: any = [];

    console.log('event:', files);
    const uploadFile = files![0];
    const reader = new FileReader();
    reader.readAsDataURL(uploadFile);
    reader.onloadend = () => {
      uploadedImages.push(reader.result);
      if(setImageSrc)
        setImageSrc(uploadedImages);
    };


    if (mode === 'MODIFY') {
      modifyGroupImage({groupId, groupImg : uploadFile});
    }

  };

  const handleImageAddButtonClicked = () => {
    if(ref.current)
      ref.current.click();
  }

  return (
      <div className={styles.profileImgBox}>
        <img src={imageSrc || member} className={imageSrc ? styles.profileImg : styles.defaultImg} alt="" />
        <button onClick={handleImageAddButtonClicked}>+</button>
        <input
          ref={ref}
          id="profileImg"
          type="file"
          accept="image/*"
          style={{ display: 'none' }}
          onChange={handleUploadImg}
        />
      </div>
  );
}

export default ProfileImgBox;
