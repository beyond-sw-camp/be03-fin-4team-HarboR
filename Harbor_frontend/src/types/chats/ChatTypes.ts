export type ChatTypes = {
  id: number;
  name: string;
  status: string;
  thumb: string;
  recent: boolean;
  lastMessage: string;
  excerpt: string;
  unReadChatCount?: number;
  chatHistory: ChatHistory[];
};

export interface ChatDetailType {
  id: number;
  thumb?: string;
  status?: string;
  name?: string;
  lastMessage?: string;
  chatHistory?: ChatHistory[];
}

export interface ChatHistoryDetail {
  id: number;
  from: string[];
  to: string[];
}

export interface ChatHistory {
  from: ChatHistoryDetail;
  to: ChatHistoryDetail;
}
