import './App.css';
import {
  RouterProvider,
  createBrowserRouter,
} from 'react-router-dom';
import LandingPage from './pages/LandingPage';
import SignupPage from './pages/SignupPage';
import LoginPage from './pages/LoginPage';
import ProfilePage from './pages/ProfilePage';
import MbtiPage from './pages/MbtiPage';
import {
  QueryClient,
  QueryClientProvider,
} from '@tanstack/react-query';
import GroupPage from './pages/GroupPage';
import ProtectedRoute from './components/common/ProtectedRoute';
import MainPage from './pages/MainPage';
import CalendarPage from './pages/CalendarPage';
import IntroducePage from './pages/IntroducePage';
import MbtiResultPage from './pages/MbtiResultPage';
import AuthenticatedRoute from './components/common/AuthenticatedRoute';
import SoloRoute from './components/common/SoloRoute';

const queryClient = new QueryClient();

const router = createBrowserRouter([
  {
    path: '/',
    element: <ProtectedRoute />,
    children: [
      {
        path: '/',
        element: <MainPage />,
      },

      {
        path: '/calendar',
        element: <CalendarPage />,
      },
      {
        path: '/profile',
        element: <ProfilePage />,
      },
      {
        path: '/introduce',
        element: <IntroducePage />,
      },
    ],
  },
  {
    path: '/',
    element: <SoloRoute />,
    children: [
      {
        path: '/group',
        element: <GroupPage />,
      },
    ]
  },
  {
    path: '/',
    element: <AuthenticatedRoute />,
    children: [
      {
        path: '/signup',
        element: <SignupPage />,
      },
      {
        path: '/login',
        element: <LoginPage />,
      },
      {
        path: '/landing',
        element: <LandingPage />,
      },
    ],
  },

  {
    path: '/mbti/:mbtiId',
    element: <MbtiPage />,
  },
  {
    path: '/result',
    element: <MbtiResultPage />,
  },
]);

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={router} />
    </QueryClientProvider>
  );
}

export default App;
