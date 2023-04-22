#include "bits/stdc++.h"

using namespace std;

int N;
priority_queue<int, vector<int>, greater<int>> cards;

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N;
    for (int i = 0; i < N; ++i) {
        int tmp;
        cin >> tmp;
        cards.push(tmp);
    }

    int answer = 0;
    while (cards.size() > 1) {
        int size = cards.size();
        for (int i = 0; i < size / 2; ++i) {
            if (i == size) {
                answer += cards.top();
                cards.pop();
            } else {
                int card1 = cards.top();
                cards.pop();
                int card2 = cards.top();
                cards.pop();
                answer += card1;
                answer += card2;
                cards.push(card1 + card2);
            }
        }
    }

    cout << answer;
    return 0;
}
