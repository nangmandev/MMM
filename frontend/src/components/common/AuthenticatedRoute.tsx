import { Navigate, Outlet } from 'react-router-dom';
import userStore from '../../stores/userStore';

function AuthenticatedRoute() {
  const { isLogin, isRecorded } = userStore();
  if (isLogin && !isRecorded) {
    return <Navigate to="/mbti/0" />;
  } else if (isLogin) {
    return <Navigate to="/" />;
  }

  return (
    <>
      <Outlet />
    </>
  );
}

export default AuthenticatedRoute;
