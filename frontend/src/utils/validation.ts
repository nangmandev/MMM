export function checkNicknameValidation(input: string) {
  if (input.length < 2 || input.length > 10) {
    return false;
  }

  // 입력값이 한글, 영어(대문자 또는 소문자), 숫자로만 구성되어 있는지 검사
  const regex = /^[가-힣a-zA-Z0-9]+$/;
  if (!regex.test(input)) {
    return false;
  }

  return true;
}

export function checkEmailValidation(input: string) {
  const emailRegex: RegExp = /@/;

  // 정규 표현식을 사용하여 이메일 주소에 @ 기호가 포함되어 있는지 확인하고 결과를 반환
  return emailRegex.test(input);
}

export function checkPasswordValidation(input: string): boolean {
    // 비밀번호의 길이가 8~20자인지 확인
    if (input.length < 8 || input.length > 20) {
      return false;
    }
  
    // 각각 영어(대소문자), 숫자, 특수문자가 최소 1개 이상 포함되어 있는지 확인하는 정규 표현식
    const regex: RegExp = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{8,20}$/;
  
    // 정규 표현식을 사용하여 비밀번호가 조건에 부합하는지 확인하고 결과를 반환
    return regex.test(input);
  }



export function checkGroupNameValidation(input: string) {
  if (input.length < 2 || input.length > 20) {
    return false;
  }

  // 입력값이 한글, 영어(대문자 또는 소문자), 숫자로만 구성되어 있는지 검사
  const regex = /^[가-힣a-zA-Z0-9]+$/;
  if (!regex.test(input)) {
    return false;
  }

  return true;
}