
export function formatDate(timestamp:number) {
  const date = new Date(timestamp);
  return `${date.toISOString().split('T')[0].replace(/-/g,'.').substring(2)}  ${date.getHours()}:${date.getMinutes()}`;
}

