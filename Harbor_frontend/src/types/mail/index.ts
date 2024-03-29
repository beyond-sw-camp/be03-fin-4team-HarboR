export interface MailProps {
  id: string;
  subject: string;
  isRead: boolean;
  isChecked: boolean;
  important: boolean;
  starred: boolean;
  time: Date;
  promotions: boolean;
  forums: boolean;
  attach: boolean;
  sent: boolean;
  draft: boolean;
  spam: boolean;
  trash: boolean;
  profile: {
    avatar: string;
    name: string;
    email: string;
    to: string;
  };
  sender: {
    avatar: string;
    name: string;
    email: string;
    to: string;
    about: string;
  };
  message: string;
  attachments: {
    id: string;
    image: string;
    title: string;
  }[];
}

export interface MailDetailsProps {
  handleUserDetails: (e: MouseEvent | undefined, v: MailProps | null) => void;
  data: MailProps | null;
  handleStarredChange: (e: MouseEvent, d: MailProps | null) => void;
  handleImportantChange: (e: MouseEvent, d: MailProps | null) => void;
}
