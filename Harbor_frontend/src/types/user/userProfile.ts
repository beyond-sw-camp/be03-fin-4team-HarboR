export type ProfileProgress = {
  label: string;
  variant: string;
  value: number;
};

export type Group = {
  id: string;
  avatar: string;
  name: string;
};

export type UserProfile = {
  id?: string;
  avatar?: string;
  image?: string;
  name?: string;
  role?: string;
  about?: string;
  email?: string;
  work_email?: string;
  personal_email?: string;
  phone?: string;
  work_phone?: string;
  personal_phone?: string;
  birthdayText?: string;
  lastMessage?: string;
  status?: string;
  friends?: number;
  followers?: number;
  contact?: string;
  company?: string;
  location?: string;
  online_status?: string;
  unReadChatCount?: number;
  groups?: Group[];
  time?: string;
  tier?: string;
  Progress?: ProfileProgress;
};
