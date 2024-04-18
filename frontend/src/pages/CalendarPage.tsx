import Calendar from 'react-calendar';
import '../styles/calendarPage/CalendarPage.css';
import moment from 'moment';

// type ValuePiece = Date | null;

// type Value = ValuePiece | [ValuePiece, ValuePiece];

function CalendarPage() {
  // const curDate = new Date(); // 현재 날짜
  // const [value, onChange] = useState<Value>(new Date());
  // const activeDate = moment(value).format('YYYY-MM-DD'); // 클릭한 날짜 (년-월-일))

  // 일기 작성 날짜 리스트
  const dayList = [
    '2024-03-10',
    '2024-03-21',
    '2024-04-02',
    '2024-04-14',
    '2024-04-27',
  ];

  // 각 날짜 타일에 컨텐츠 추가
  const addContent = ({ date }: any) => {
    // 해당 날짜(하루)에 추가할 컨텐츠의 배열
    const contents = [];

    // date(각 날짜)가  리스트의 날짜와 일치하면 해당 컨텐츠(이모티콘) 추가
    if (
      dayList.find((day) => day === moment(date).format('YYYY-MM-DD'))
    ) {
      contents.push(
        <>
          <div className="dot" style={{width: '20px', height: '20px', backgroundColor: 'rgba(#FF0000, 0.3)', borderRadius: '10px', margin: '8px'}}></div>
        </>
      );
    }
    return <div>{contents}</div>; // 각 날짜마다 해당 요소가 들어감
  };

  return (
    // <Calendar tileContent={addContent} />
    <Calendar
            locale="en"
            // onChange={onChange}
            // value={value}
            calendarType="hebrew"
            next2Label={null}
            prev2Label={null}
            // formatDay={(locale, date) => moment(date).format('D')}
            formatDay={(date) => moment(date).format('D')}
            tileContent={addContent}
            showNeighboringMonth={false}
            // onActiveStartDateChange={({ activeStartDate }) =>
            //   getActiveMonth(activeStartDate)
            // }
          />
  )

  
  
  ;
}

export default CalendarPage;
