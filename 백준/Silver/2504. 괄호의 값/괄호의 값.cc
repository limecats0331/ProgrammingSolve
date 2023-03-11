#include "bits/stdc++.h"

using namespace std;

//참고 : https://mjmjmj98.tistory.com/70

int find_score(string str) {
    stack<char> S;
    int score = 0;
    int tmp = 1;
    char prev;

    for (char c: str) {
        if (c == '(') {
            S.push(c);
            tmp *= 2;
        } else if (c == ')') {
            if (S.empty() || S.top() != '(') {
                return 0;
            } else if (prev == '(') {
                S.pop();
                score += tmp;
                tmp /= 2;
            } else {
                S.pop();
                tmp /= 2;
            }
        } else if (c == '[') {
            S.push(c);
            tmp *= 3;
        } else if (c == ']') {
            if (S.empty() || S.top() != '[') {
                return 0;
            } else if (prev == '[') {
                S.pop();
                score += tmp;
                tmp /= 3;
            } else {
                S.pop();
                tmp /= 3;
            }
        }
        prev = c;
    }

    if (!S.empty()) return 0;

    return score;
}

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);

    string input;
    getline(cin, input);

    cout << find_score(input);

    return 0;
}