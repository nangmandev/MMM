import { Link, useLocation } from 'react-router-dom';
// import calendar_white from '../../assets/images/calendar_white.png';
// import calendar_blue from '../../assets/images/calendar_blue.png';
import group_white from '../../assets/images/group_white.png';
import group_blue from '../../assets/images/group_blue.png';
import home_white from '../../assets/images/home_white.png';
import home_blue from '../../assets/images/home_blue.png';
import mbti_white from '../../assets/images/mbti_white.png';
import mbti_blue from '../../assets/images/mbti_blue.png';
import profile_white from '../../assets/images/profile_white.png';
import profile_blue from '../../assets/images/profile_blue.png';
import styles from '../../styles/common/NavBar.module.css';

function NavBar() {
  const location = useLocation();

  return (
    <nav>
      {/* <Link
        to="/calendar"
        className={
          location.pathname === '/calendar'
            ? styles.active
            : styles.inActive
        }
      >
        <div>
          {location.pathname === '/calendar' ? (
            <img src={calendar_blue} alt="" />
          ) : (
            <img src={calendar_white} alt="" />
          )}

          <span>먹어쓰</span>
        </div>
      </Link> */}
      <Link
        to="/group"
        className={
          location.pathname === '/group'
            ? styles.active
            : styles.inActive
        }
      >
        <div>
          {location.pathname === '/group' ? (
            <img src={group_blue} alt="" />
          ) : (
            <img src={group_white} alt="" />
          )}
          <span>먹그룹</span>
        </div>
      </Link>
      <Link
        to="/"
        className={
          location.pathname === '/' ? styles.active : styles.inActive
        }
      >
        <div>
          {location.pathname === '/' ? (
            <img src={home_blue} alt="" />
          ) : (
            <img src={home_white} alt="" />
          )}
          <span>홈</span>
        </div>
      </Link>
      <Link
        to="/introduce"
        className={
          location.pathname === '/introduce'
            ? styles.active
            : styles.inActive
        }
      >
        <div>
          {location.pathname === '/introduce' ? (
            <img src={mbti_blue} alt="" />
          ) : (
            <img src={mbti_white} alt="" />
          )}
          <span>먹BTI</span>
        </div>
      </Link>
      <Link
        to="/profile"
        className={
          location.pathname === '/profile'
            ? styles.active
            : styles.inActive
        }
      >
        <div>
          {location.pathname === '/profile' ? (
            <img src={profile_blue} alt="" />
          ) : (
            <img src={profile_white} alt="" />
          )}
          <span>내정보</span>
        </div>
      </Link>
    </nav>
  );
}

export default NavBar;
