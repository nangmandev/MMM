import { Link, Navigate, Outlet } from 'react-router-dom';
import NavBar from './NavBar';
import userStore from '../../stores/userStore';
import styles from '../../styles/common/ProtectedRoute.module.css';
import subLogo from '../../assets/images/subLogo.png';

function ProtectedRoute() {
  const { isLogin } = userStore();
  if (!isLogin) {
    return <Navigate to='/landing' />;
  }

  return (
    <div className={styles.wrapper}>
      <Link to='/'>
        <img className={styles.subLogo} src={subLogo} alt='' />
      </Link>
      <Outlet />
      <NavBar />
    </div>
  );
}

export default ProtectedRoute;
