const NotificationMessage = ({ notification }) => {
  return (
    <>
      <div id="notificationHeader">
        {/* image is optional */}
        {notification.image && (
          <div id="imageContainer">
            <img src={notification.image} alt="notification" width={100} />
          </div>
        )}
        <span>{notification.title}</span>
      </div>
      <div id="notificationBody">{notification.body}</div>
    </>
  );
};

export default NotificationMessage;
